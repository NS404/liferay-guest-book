package com.liferay.docs.dispatch.job.scheduler.task.executor;


import com.liferay.dispatch.executor.BaseDispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutorOutput;
import com.liferay.dispatch.model.DispatchTrigger;
import org.osgi.service.component.annotations.Component;

/**
 * @author enes.jashari
 */
@Component(
		property = {
				"dispatch.task.executor.name=s7a3",
				"dispatch.task.executor.type=s7a3"
		},
		service = DispatchTaskExecutor.class
)
public class MyDispatchTaskExecutor extends BaseDispatchTaskExecutor {

	@Override
	public void doExecute(DispatchTrigger dispatchTrigger, DispatchTaskExecutorOutput dispatchTaskExecutorOutput) {

		System.out.println("task executed");
//		if (_log.isInfoEnabled()) {
//			_log.info("Invoking #doExecute(DispatchTrigger, " + "DispatchTaskExecutorOutput)");
//		}
	}

	@Override
	public String getName() {
		return "s7a3";
	}

	//private static final Log _log = LogFactoryUtil.getLog(MyDispatchTaskExecutor.class);

}