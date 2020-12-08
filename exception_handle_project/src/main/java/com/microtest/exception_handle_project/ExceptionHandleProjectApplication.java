package com.microtest.exception_handle_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class ExceptionHandleProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExceptionHandleProjectApplication.class, args);
		System.out.println("createLogFile result:"+new File("C:\\Users\\etc01\\Desktop\\testFile/image").mkdir());
		System.out.println("createImageFile result:"+new File("C:\\Users\\etc01\\Desktop\\testFile/ExceptionApplication").mkdir());
	}

}
