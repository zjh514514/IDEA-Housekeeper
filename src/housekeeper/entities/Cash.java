package housekeeper.entities;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Cash {

    private Date time;
    private Double money;
    private String site;
    private String account;
    private Double balance;
    private String item;
    private String subItem;
    private String remark;
    private Integer itemId;
    private Integer subItemId;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getSubItem() {
        return subItem;
    }

    public void setSubItem(String subItem) {
        this.subItem = subItem;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getSubItemId() {
        return subItemId;
    }

    public void setSubItemId(Integer subItemId) {
        this.subItemId = subItemId;
    }

    @Override
    public String toString() {
        return "Cash{" +
                "time=" + time +
                ", money=" + money +
                ", site='" + site + '\'' +
                ", account='" + account + '\'' +
                ", balance=" + balance +
                ", item='" + item + '\'' +
                ", subItem='" + subItem + '\'' +
                ", remark='" + remark + '\'' +
                ", itemId=" + itemId +
                ", subItemId=" + subItemId +
                '}';
    }
}
