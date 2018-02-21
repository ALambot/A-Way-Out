package com.dolphin.awayout;

/**
 * Created by siasn on 21-02-18.
 */

public abstract class InventoryObserver {
    protected InventoryGridView inventoryGridView;
    public abstract void update(int state, MyObject object, int x, int y);
}
