from proto.edu.uci.ics.amber.engine.architecture.worker import (
    WorkerDebugCommandV2,
)
from .handler_base import Handler
from ..managers.context import Context


class WorkerDebugCommandHandler(Handler):
    cmd = WorkerDebugCommandV2

    def __call__(self, context: Context, command: cmd, *args, **kwargs):
        # translate the command with the context.
        translated_command = self.translate_debug_command(command, context)

        # send the translated command to debugger to consume later.
        context.debug_manager.put_debug_command(translated_command)

        # allow MainLoop to switch into DataProcessor.
        context.main_loop._resume_dp()

    @staticmethod
    def translate_debug_command(command: cmd, context: Context) -> str:
        """
        This method cleans up, reformats, and then translates a debug command into
        a command that can be understood by the debugger.

        For example, it adds the UDF code context.

        :param command:
        :param context:
        :return:
        """
        debug_command, *debug_args = command.cmd.strip().split()
        module_name = context.operator_manager.operator_module_name
        if debug_command in ["b", "break"] and len(debug_args) > 0:
            # b(reak) ([filename:]lineno | function) [, condition]¶
            translated_command = (
                f"{debug_command} {module_name}:{debug_args[0]} "
                f"{' '.join(debug_args[1:])}"
            )
        else:
            translated_command = f"{debug_command} {' '.join(debug_args)}"

        translated_command = translated_command.strip()
        return translated_command
