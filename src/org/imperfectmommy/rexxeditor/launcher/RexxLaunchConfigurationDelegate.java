package org.imperfectmommy.rexxeditor.launcher;

import java.io.File;
import java.text.MessageFormat;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.LaunchConfigurationDelegate;

public class RexxLaunchConfigurationDelegate extends
		LaunchConfigurationDelegate {
	private String fRexxRuntime = "";
	private Process fInferiorProcess;

    public void launch(ILaunchConfiguration configuration, String mode, ILaunch launch, IProgressMonitor monitor) throws CoreException {

        try {

            fRexxRuntime = configuration.getAttribute(IRexxLaunchConstants.REXX_LAUNCH_INTERPRETER, "");

            if (monitor == null) {
                new NullProgressMonitor();
            } else {
            }

            monitor.beginTask(MessageFormat.format("Launching {0}...", configuration.getName()), 3);
            if (monitor.isCanceled()) {

                return;
            }
            String file = configuration.getAttribute(IRexxLaunchConstants.REXX_LAUNCH_STRING_FILE, "");

            String workingDirectory = configuration.getAttribute(IRexxLaunchConstants.REXX_LAUNCH_WORKING_DIRECTORY, "");
            String params           = configuration.getAttribute(IRexxLaunchConstants.REXX_LAUNCH_PARAMETERS, "");

            File fworkingDirectory = null;

            if (workingDirectory != "" && workingDirectory != null) {
                fworkingDirectory = new File(workingDirectory);
            }

            fInferiorProcess = DebugPlugin.exec(new String[] { fRexxRuntime, file, params }, fworkingDirectory);

            DebugPlugin.newProcess(launch, fInferiorProcess, "Rexx Session");

            monitor.worked(1);
        } catch (CoreException e) {

        }

    }
	
	

}
