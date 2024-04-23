package com.cs.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {

	public static String saveFile(Path uploadPath, String fileName, MultipartFile multipartFile) throws IOException {

		if (!Files.exists(uploadPath)) {
			try {
				Files.createDirectories(uploadPath);
			} catch (IOException e) {
				throw new IOException("Could not create directory: " + uploadPath, e);
			}
		}

		try {
			String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;
			Path filePath = uploadPath.resolve(uniqueFileName);
			try (InputStream inputStream = multipartFile.getInputStream()) {
				Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			}
			return uniqueFileName;
		} catch (IOException e) {
			throw new IOException("Could not save file: " + fileName, e);
		}
	}
}
/*
 * public class FileUploadUtil {
 * 
 * public static String saveFile(Path uploadPath, String fileName, MultipartFile
 * multipartFile, String fileCode) throws IOException {
 * 
 * uploadPath = Paths.get("src/main/resources/static/clic/img/");
 * 
 * if (!Files.exists(uploadPath)) { Files.createDirectories(uploadPath); }
 * 
 * // String fileCode = "test";
 * 
 * try (InputStream inputStream = multipartFile.getInputStream()) { fileCode =
 * UUID.randomUUID().toString(); // Generate a unique code Path filePath =
 * uploadPath.resolve(fileCode +"_" + fileName); Files.copy(inputStream,
 * filePath, StandardCopyOption.REPLACE_EXISTING); } catch (IOException ioe) {
 * if (ioe instanceof FileNotFoundException) { throw new
 * IOException("Could not save file: File not found", ioe); // Specific message
 * } else { throw new IOException("Could not save file: " + fileName, ioe); } }
 * 
 * try (InputStream inputStream = multipartFile.getInputStream()) { Path
 * filePath = uploadPath.resolve(fileCode + "/" + fileName);
 * Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING); }
 * catch (IOException ioe) { throw new IOException("Could not save file: " +
 * fileName, ioe); }
 * 
 * 
 * return fileCode; }
 * 
 * }
 */
