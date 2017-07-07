package com.duanxian.shell;


import com.jcraft.jsch.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lionsong on 2017/7/6.
 */
public class RemoteShellExecutor {
    private static final Logger LOGGER = LogManager.getLogger(RemoteShellExecutor.class);
    private static final JSch JSCH = new JSch();

    public Session createSession(String username, String password, String host, int port) throws Exception {
        Session session = null;
        try {
            session = JSCH.getSession(username, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setTimeout(30000);
        } catch (JSchException e) {
            LOGGER.error(e.getMessage());
        }
        if (session == null) {
            throw new Exception("Failed to create session.");
        } else {
            session.connect();
        }
        return session;
    }

    public List<String> execShell(Session session, String shell, String charset) {
        if (session == null) {
            try {
                throw new Exception("Session is null.");
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                System.exit(1);
            }
        }
        List<String> output = new ArrayList<String>();
        try {
            Channel channel = session.openChannel("exec");
            String cmd = "bash " + shell;
            ((ChannelExec) channel).setCommand(cmd);
            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);
            channel.connect();
            LOGGER.info("Channel is connected.");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(channel.getInputStream(), Charset.forName(charset)));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                output.add(line);
            }
            bufferedReader.close();
            channel.disconnect();
//            LOGGER.info("Channel is disconnected.");
//            session.disconnect();

        } catch (JSchException e) {
            LOGGER.error(e.getMessage());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return output;

    }


}
