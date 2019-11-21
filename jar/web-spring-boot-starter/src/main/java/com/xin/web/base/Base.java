package com.xin.web.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Transient;

/**
 * @author creator mafh 2019/11/21 17:58
 * @author updater
 * @version 1.0.0
 */
public class Base {

    @Transient
    protected transient Logger logger = LoggerFactory.getLogger(getClass());
}
