package org.indigo.cloudproviderranker.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.FileUtils;

public class TestUtil {

	public static String getFileContentAsString(String fileUri) throws IOException {
		return FileUtils.readFileToString(new File(fileUri), StandardCharsets.UTF_8);
	}
}
