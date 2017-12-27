package housekeeper.service.impl;

import housekeeper.dao.ItemDao;
import housekeeper.dao.SubItemDao;
import housekeeper.entities.Item;
import housekeeper.entities.SubItem;
import housekeeper.service.ItemsService;
import housekeeper.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsServiceImpl implements ItemsService {

	@Autowired
	private ItemDao itemDao;
	@Autowired
	private SubItemDao subItemDao;
	@Autowired
    private OperatorService operatorService;

	@Autowired
	private Item item;
	@Autowired
	private SubItem subItem;

	@Override
	public String addItems(String itemName, Integer type) {
		try {
			if (itemName == null)
				itemName = "";
			if (type != null) {
				if (type != 0 && type != 1)
					type = -1;
				if (!itemName.equals("") && type != -1 && itemDao.queryByName(itemName).size() == 0) {
					item.setItemName(itemName);
					item.setType(type);
					itemDao.save(item);
					return "SUCCESS";
				} else {
					return "FAILED";
				}
			} else {
				return "FAILED";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}

	}

	@Override
	public String addSubItems(String subItemName, Integer itemId) {
		try {
			if (subItemName == null)
				subItemName = "";
			if (!subItemName.equals("") && itemId != null && subItemDao.queryByName(subItemName).size() == 0
					&& itemDao.queryById(itemId).size() != 0) {
				item.setItemId(itemId);
				subItem.setSubItemName(subItemName);
				subItem.setItem(item);
				System.out.println("1");
				subItemDao.save(subItem);
				return "SUCCESS";
			} else {
				return "FAILED";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}

	}

	@Override
	public String delete(Integer id,String which) {
		return operatorService.delete(id,itemDao.queryById(id).size(),which);
	}

	@Override
	public String updateItem(String itemName, Integer id) {
		try {
			if (itemName == null)
				itemName = "";
			if (id != null && !itemName.equals("") && itemDao.queryByName(itemName).size() == 0
					&& itemDao.queryById(id).size() != 0) {
				System.out.println("1");
				itemDao.update(id, itemName);
				return "SUCCESS";
			} else {
				return "FAILED";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}
	}

	@Override
	public String updateSubItem(String subItemName, Integer id) {
		try {
			if (subItemName == null)
				subItemName = "";
			if (id != null && !subItemName.equals("") && subItemDao.queryByName(subItemName).size() == 0
					&& subItemDao.queryById(id).size() != 0) {
				subItemDao.update(id, subItemName);
				return "SUCCESS";
			} else {
				return "FAILED";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}
	}

	@Override
	public List queryItem(Integer type) {
		if (type != null) {
			if (type != 0 && type != 1)
				type = -1;
			if (type != -1) {
				List items = itemDao.queryByType(type);
				if (items.size() != 0) {
					return items;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public List querySubItem(Integer itemId) {
		if (itemId != null) {
//			item.setItemId(itemId);
			List subItems = subItemDao.queryByItem(itemId);
			if (subItems.size() != 0) {
				return subItems;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

}
