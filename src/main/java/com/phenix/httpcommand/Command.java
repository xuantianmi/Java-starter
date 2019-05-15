package com.phenix.httpcommand;

import java.util.Map;

/**
 * @author Qujing
 */
public interface Command {

    /**
     *
     * @param paramMap
     * @return
     */
    Result execute(Map<String, Object> paramMap);

}
