package kineticnetwork.net.chat.listener;

import kineticnetwork.net.chat.Chat;
import kineticnetwork.net.chat.Notify;
import kineticnetwork.net.chat.Utils.TextCheck;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.Getter;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.event.item.inventory.UpdateAnvilEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.InventoryArchetypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.slot.InputSlot;
import org.spongepowered.api.item.inventory.slot.OutputSlot;
import org.spongepowered.api.text.Text;

import java.io.IOException;
import java.util.Arrays;

public class onAnvilUse {
    Notify notify = new Notify();
    TextCheck check = new TextCheck();
    private String Itemname;

    @Listener
    public void onItemForge(ClickInventoryEvent event, @First Player player, @Getter("getTargetInventory") Inventory inventory) {
        //Todo this needs to be redone it is mess
        if (inventory.getArchetype() == InventoryArchetypes.ANVIL) {

            // Loop through the transactions for this event, there should really only be 1
            event.getTransactions().forEach(slotTransaction -> {
                if (slotTransaction.getSlot() instanceof InputSlot) {
                    ItemStack originalStacka = slotTransaction.getOriginal().createStack();
                    if (originalStacka.getType() != ItemTypes.AIR) {

                        // Grab the display name for the item
                        Text itemName = originalStacka.get(Keys.DISPLAY_NAME).orElse(Text.of(originalStacka.getTranslation()));
                        Itemname  = itemName.toPlain();// Saves Input slots input

                    }}
                    // If the player clicked on an output slot, then we know they've forged a new item
                    if (slotTransaction.getSlot() instanceof OutputSlot &&
                            slotTransaction.getOriginal().equals(event.getCursorTransaction().getDefault())) {

                        // Get the original itemstack from the transaction, as the final should be air if crafting a new item
                        ItemStack originalStack = slotTransaction.getOriginal().createStack();

                        // Check to make sure that the player didn't click on the empty output slot with an item
                        if (originalStack.getType() != ItemTypes.AIR) {

                            // Grab the display name for the item
                            Text itemName = originalStack.get(Keys.DISPLAY_NAME).orElse(Text.of(originalStack.getTranslation()));
                            String itemNamePlain = itemName.toPlain();
                            if (itemNamePlain.equals(Itemname)){
                                return;
                            }
                            notify.imformStaffAnvil(player, itemNamePlain);
                            try {
                                if (check.checkmessage(itemNamePlain, player, "Anvil", "null")>0) {
                                    event.setCancelled(true);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    }

            });
        }
    }
}



