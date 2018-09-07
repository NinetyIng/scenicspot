package com.ythd.ower.server.service.content;

import com.ythd.ower.data.dao.EABaseService;
import com.ythd.ower.data.dao.EADao;
import com.ythd.ower.server.mapper.content.SubjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author: liujunbo
 * @since: 2018/8/1
 * @version: $Revision$
 */
@Service
public class SubjectService extends EABaseService {

  @Autowired
  private SubjectMapper subjectMapper;

  @Override
  public EADao getDao() {
    return subjectMapper;
  }
}
