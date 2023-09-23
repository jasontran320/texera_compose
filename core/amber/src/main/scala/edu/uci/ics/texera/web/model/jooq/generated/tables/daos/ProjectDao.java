/*
 * This file is generated by jOOQ.
 */
package edu.uci.ics.texera.web.model.jooq.generated.tables.daos;


import edu.uci.ics.texera.web.model.jooq.generated.tables.Project;
import edu.uci.ics.texera.web.model.jooq.generated.tables.records.ProjectRecord;

import java.sql.Timestamp;
import java.util.List;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;
import org.jooq.types.UInteger;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ProjectDao extends DAOImpl<ProjectRecord, edu.uci.ics.texera.web.model.jooq.generated.tables.pojos.Project, UInteger> {

    /**
     * Create a new ProjectDao without any configuration
     */
    public ProjectDao() {
        super(Project.PROJECT, edu.uci.ics.texera.web.model.jooq.generated.tables.pojos.Project.class);
    }

    /**
     * Create a new ProjectDao with an attached configuration
     */
    public ProjectDao(Configuration configuration) {
        super(Project.PROJECT, edu.uci.ics.texera.web.model.jooq.generated.tables.pojos.Project.class, configuration);
    }

    @Override
    public UInteger getId(edu.uci.ics.texera.web.model.jooq.generated.tables.pojos.Project object) {
        return object.getPid();
    }

    /**
     * Fetch records that have <code>pid BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<edu.uci.ics.texera.web.model.jooq.generated.tables.pojos.Project> fetchRangeOfPid(UInteger lowerInclusive, UInteger upperInclusive) {
        return fetchRange(Project.PROJECT.PID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>pid IN (values)</code>
     */
    public List<edu.uci.ics.texera.web.model.jooq.generated.tables.pojos.Project> fetchByPid(UInteger... values) {
        return fetch(Project.PROJECT.PID, values);
    }

    /**
     * Fetch a unique record that has <code>pid = value</code>
     */
    public edu.uci.ics.texera.web.model.jooq.generated.tables.pojos.Project fetchOneByPid(UInteger value) {
        return fetchOne(Project.PROJECT.PID, value);
    }

    /**
     * Fetch records that have <code>name BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<edu.uci.ics.texera.web.model.jooq.generated.tables.pojos.Project> fetchRangeOfName(String lowerInclusive, String upperInclusive) {
        return fetchRange(Project.PROJECT.NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>name IN (values)</code>
     */
    public List<edu.uci.ics.texera.web.model.jooq.generated.tables.pojos.Project> fetchByName(String... values) {
        return fetch(Project.PROJECT.NAME, values);
    }

    /**
     * Fetch records that have <code>description BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<edu.uci.ics.texera.web.model.jooq.generated.tables.pojos.Project> fetchRangeOfDescription(String lowerInclusive, String upperInclusive) {
        return fetchRange(Project.PROJECT.DESCRIPTION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>description IN (values)</code>
     */
    public List<edu.uci.ics.texera.web.model.jooq.generated.tables.pojos.Project> fetchByDescription(String... values) {
        return fetch(Project.PROJECT.DESCRIPTION, values);
    }

    /**
     * Fetch records that have <code>owner_id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<edu.uci.ics.texera.web.model.jooq.generated.tables.pojos.Project> fetchRangeOfOwnerId(UInteger lowerInclusive, UInteger upperInclusive) {
        return fetchRange(Project.PROJECT.OWNER_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>owner_id IN (values)</code>
     */
    public List<edu.uci.ics.texera.web.model.jooq.generated.tables.pojos.Project> fetchByOwnerId(UInteger... values) {
        return fetch(Project.PROJECT.OWNER_ID, values);
    }

    /**
     * Fetch records that have <code>creation_time BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<edu.uci.ics.texera.web.model.jooq.generated.tables.pojos.Project> fetchRangeOfCreationTime(Timestamp lowerInclusive, Timestamp upperInclusive) {
        return fetchRange(Project.PROJECT.CREATION_TIME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>creation_time IN (values)</code>
     */
    public List<edu.uci.ics.texera.web.model.jooq.generated.tables.pojos.Project> fetchByCreationTime(Timestamp... values) {
        return fetch(Project.PROJECT.CREATION_TIME, values);
    }

    /**
     * Fetch records that have <code>color BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<edu.uci.ics.texera.web.model.jooq.generated.tables.pojos.Project> fetchRangeOfColor(String lowerInclusive, String upperInclusive) {
        return fetchRange(Project.PROJECT.COLOR, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>color IN (values)</code>
     */
    public List<edu.uci.ics.texera.web.model.jooq.generated.tables.pojos.Project> fetchByColor(String... values) {
        return fetch(Project.PROJECT.COLOR, values);
    }
}
