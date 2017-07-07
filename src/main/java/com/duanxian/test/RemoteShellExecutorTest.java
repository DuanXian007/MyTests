package com.duanxian.test;

import com.duanxian.shell.RemoteShellExecutor;
import com.jcraft.jsch.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Created by Lionsong on 2017/7/6.
 */
public class RemoteShellExecutorTest {
    private static final Logger LOGGER = LogManager.getLogger(RemoteShellExecutorTest.class);

    public static void main(String[] args) {
        RemoteShellExecutor rse = new RemoteShellExecutor();
        try {
            Session session = rse.createSession("duanxian", "Gg3fgMc8zt", "192.168.56.101", 22);
            List<String> output;
            output = rse.execShell(session, "/home/duanxian/Projects/Shell/TestShell.sh", "UTF-8");
            for (String line : output) {
                LOGGER.info("Output: " + line);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
}
