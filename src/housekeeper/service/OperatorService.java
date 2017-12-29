package housekeeper.service;

public interface OperatorService {

    Object getList(Object Object, Object... id);

    String delete(Integer id, Integer size, String method);
}
