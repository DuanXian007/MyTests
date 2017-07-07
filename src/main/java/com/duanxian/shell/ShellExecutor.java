package com.duanxian.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lionsong on 2017/7/6.
 */
public class ShellExecutor {
    public void runShell(String shell) {
        String cmd = "chmod a+x " + shell;
        try {
            Process proc = Runtime.getRuntime().exec(cmd);
            proc.waitFor();
            cmd = "bash " + shell;
            proc = Runtime.getRuntime().exec(cmd);
            String line;
            List<String> output = new ArrayList<String>();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            while ((line = bufferedReader.readLine()) != null) {
                output.add(line);
                bufferedReader.close();
                proc.waitFor();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
