package me.desht.pneumaticcraft.common.thirdparty.ae2;

import java.util.List;

import net.minecraft.item.ItemStack;
import me.desht.pneumaticcraft.api.item.IInventoryItem;
import appeng.api.AEApi;
import appeng.api.storage.ICellRegistry;
import appeng.api.storage.IMEInventoryHandler;
import appeng.api.storage.channels.IItemStorageChannel;
import appeng.api.storage.data.IAEItemStack;
import appeng.api.storage.data.IItemList;

public class AE2DiskInventoryItemHandler implements IInventoryItem{
	private final ICellRegistry cellRegistry = AEApi.instance().registries().cell();
	private final IItemStorageChannel itemChannel = AEApi.instance().storage().getStorageChannel(IItemStorageChannel.class);

	@Override
	public void getStacksInItem(ItemStack stack, List<ItemStack> curStacks){
		IMEInventoryHandler<IAEItemStack> cellInventoryHandler = cellRegistry.getCellInventory(stack, null,	itemChannel);
		if (cellInventoryHandler != null) {
			IItemList<IAEItemStack> cellItemList = itemChannel.createList();
			cellInventoryHandler.getAvailableItems(cellItemList);
			for (IAEItemStack aeStack : cellItemList) {
				ItemStack st = aeStack.createItemStack();
				st.setCount((int) aeStack.getStackSize());
				curStacks.add(st);
			}
		}
	}
}
