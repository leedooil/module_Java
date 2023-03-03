package com.inzent;

import java.io.File;
import java.util.Scanner;

public class ReplaceFileName {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// fileName에는 하위 디렉터리를 포함하고 있는 최상위 디렉터리 경로 입력
		System.out.print("디렉터리 경로를 입력하세요 : ");
		String fileName = sc.next();
		ReplaceFileName replaceFileName = new ReplaceFileName();
		// 폴더 내 파일 이름 변경 메소드 호출
		replaceFileName.folderInFiles(fileName);
	}

	// 폴더 내 파일 이름 변경 메소드
	private void folderInFiles(String path) {
		File folder = new File(path);
		// 해당 경로에 있는 폴더 리스트를 배열로 저장
		File files[] = folder.listFiles();
		
		System.out.println(folder.getName() + " : " + folder.isDirectory() + ", " + files.length);
		
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			// if문을 이용한 재귀함수 호출로 폴더 내 파일들 까지 모두 호출
			if (file.isDirectory()) {
				folderInFiles(file.getPath());
			} else {
				// 더 이상 디렉토리가 아니면 파일 이름을 수정
				String originalString = "216";
				String replaceString = "219";
				String newNameFile = file.toString().replace(originalString, replaceString);
				file.renameTo(new File(newNameFile));

				FileContentConvert fcc = new FileContentConvert();
				// 특정 파일 내용 수정 메소드 호출
				fcc.fileContentConvert(newNameFile);
			}
		}
	}
}
