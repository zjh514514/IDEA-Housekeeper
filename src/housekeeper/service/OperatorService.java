package housekeeper.service;

import java.util.List;

public interface OperatorService {

    List getList(Integer id, List list);

    String delete(Integer id, Integer size, String method);
}
