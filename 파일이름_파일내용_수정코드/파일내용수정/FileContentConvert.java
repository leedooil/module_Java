package com.inzent;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileContentConvert {
	
	// 특정 파일 내용 수정 메소드
	public void fileContentConvert(String fileName) {
		// 원본 파일 저장
		File inputFile = new File(fileName);
		// 수정 파일 저장
		File outputFile = new File(fileName + ".temp");
		FileInputStream fileInputStream = null;
		BufferedReader bufferedReader = null;
		FileOutputStream fileOutputStream = null;
		BufferedWriter bufferedWriter = null;
		boolean result = false;
		// 버퍼 사용시 반드시 try-catch문 사용
		try {
			// 원본 파일을 스트림에 저장
			fileInputStream = new FileInputStream(inputFile);
			// 수정 파일을 스트림에 저장
			fileOutputStream = new FileOutputStream(outputFile);
			// 읽어올 원본 파일을 버퍼에 저장
			bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
			// 새로쓸 수정 파일을 버퍼에 저장
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
			String line;
			String replaceLine;
			String originalString = "216";
			String replaceString = "219";
			// 읽어올 원본 파일의 내용이 더 이상 없을 때 까지 while문
			while ((line = bufferedReader.readLine()) != null) {
				// 원본 파일을 한 줄 한 줄 읽어서 내용 수정 
				replaceLine = line.replace(originalString, replaceString);
				// 수정한 내용을 새로쓸 파일에 저장
				bufferedWriter.write(replaceLine, 0, replaceLine.length());
				// 줄 바까서 원본 파일 내용 읽기
				bufferedWriter.newLine();
			}
			result = true;
		} catch (IOException exception1) {
			exception1.printStackTrace();
		} finally {
			try {
				// 다 읽은 원본 파일 버퍼 종료
				bufferedReader.close();
			} catch (IOException exception2) {
				exception2.printStackTrace();
			}
			try {
				// 새로쓴 수정 파일 버퍼 종료
				bufferedWriter.close();
			} catch (IOException exception3) {
				exception3.printStackTrace();
			}
			if (result) {
				// 작업이 완료 됐으면 원본 파일 삭제
				inputFile.delete();
				// 수정 파일을  원본 파일 이름으로 변경
				outputFile.renameTo(new File(fileName));
			}
		}
	}
}
