package com.markerhub.timing;


import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

@RestController
public class DeleteFile {
	int i = 0;
	//在一个特定的时间执行这个方法
	//cron表达式
	//秒  分  时  日  月  周几
	@Scheduled(cron = "0 0 * * * ?")
	public void deleFile() throws IOException {

		System.out.println("1");
		String filepath = "src/main/java/com/markerhub/Listener/test-output1/";
		File file = new File(filepath);// File类型可以是文件也可以是文件夹
		File[] files = file.listFiles();//得到所有文件和文件夹
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR,
				calendar.get(Calendar.HOUR) - 5);// 让日期加1
		Long pastTime = calendar.getTime().getTime();
		for (File f:files) {
			long fileTime = f.lastModified();
			if (pastTime > fileTime){
				f.delete();
			}


		}

	}
}