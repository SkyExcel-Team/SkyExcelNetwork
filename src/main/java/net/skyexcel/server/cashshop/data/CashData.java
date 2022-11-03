package net.skyexcel.server.cashshop.data;

public interface CashData {
    public void Increase(long Amount);

    public void Decrease(long Amount);

    public void Set(long Amount);


}
