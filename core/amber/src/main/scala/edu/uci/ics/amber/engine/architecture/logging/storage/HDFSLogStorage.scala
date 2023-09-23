package edu.uci.ics.amber.engine.architecture.logging.storage

import edu.uci.ics.amber.engine.architecture.logging.storage.DeterminantLogStorage.{
  DeterminantLogReader,
  DeterminantLogWriter
}
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}

import java.net.URI

class HDFSLogStorage(name: String, hdfsIP: String) extends DeterminantLogStorage {
  var hdfs: FileSystem = _
  val hdfsConf = new Configuration
  hdfsConf.set("dfs.client.block.write.replace-datanode-on-failure.enable", "false")
  try {
    hdfs = FileSystem.get(new URI(hdfsIP), hdfsConf)
  } catch {
    case e: Exception =>
      e.printStackTrace()
  }
  private val recoveryLogFolder: Path = new Path("/recovery-logs")
  if (!hdfs.exists(recoveryLogFolder)) {
    hdfs.mkdirs(recoveryLogFolder)
  }

  private def getLogPath: Path = {
    new Path("/recovery-logs/" + name + ".logfile")
  }

  override def getWriter: DeterminantLogWriter = {
    new DeterminantLogWriter(hdfs.append(getLogPath))
  }

  override def getReader: DeterminantLogReader = {
    val path = getLogPath
    if (hdfs.exists(path)) {
      new DeterminantLogReader(() => hdfs.open(path))
    } else {
      new EmptyLogStorage().getReader
    }
  }

  override def deleteLog(): Unit = {
    // delete log if exists
    val path = getLogPath
    if (hdfs.exists(path)) {
      hdfs.delete(path, false)
    }
  }

  override def cleanPartiallyWrittenLogFile(): Unit = {
    var tmpPath = getLogPath
    tmpPath = tmpPath.suffix(".tmp")
    copyReadableLogRecords(new DeterminantLogWriter(hdfs.create(tmpPath)))
    if (hdfs.exists(getLogPath)) {
      hdfs.delete(getLogPath, false)
    }
    hdfs.rename(tmpPath, getLogPath)
  }

  override def isLogAvailableForRead: Boolean = {
    if (hdfs.exists(getLogPath)) {
      val stats = hdfs.getFileStatus(getLogPath)
      stats.isFile && stats.getLen > 0
    } else {
      false
    }
  }
}
