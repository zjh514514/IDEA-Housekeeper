package housekeeper.service;

import java.util.List;

public interface OperatorService {

    Object getList(Object Object, Object... id);

    String delete(Integer id, Integer size, String method);

    List CashIn(Integer memberId, List list);

    List CashOut(Integer memberId, List list);
}
