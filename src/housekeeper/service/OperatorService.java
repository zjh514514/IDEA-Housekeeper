package housekeeper.service;

import java.util.List;

public interface OperatorService {

    List getList(List list, Integer... id);

    String delete(Integer id, Integer size, String method);
}
