/*
Copyright [2017] [SkelletonX]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package me.skelletonx.ExplosionPVPUtils;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;

import me.skelletonx.ExplosionPVPUtils.Disable.DisableListeners;
import me.skelletonx.ExplosionPVPUtils.ProtocolLib.WrapperPlayClientTabComplete;

public class Main extends JavaPlugin implements Listener {
	@Override
	public void onEnable() {
		if (!new File(this.getDataFolder(), "config.yml").exists()) {
			this.saveDefaultConfig();
		}
		RunProtocalLib();
        getServer().getPluginManager().registerEvents(new DisableListeners(), this);
		CommandSender cs = Bukkit.getConsoleSender();
		cs.sendMessage("§5[ExplosionPVPUtils] Plugin Habilitado - Versao (" + this.getDescription().getVersion() + ")");
		cs.sendMessage("§5[ExplosionPVPUtils] Author: SkelletonX");
	}

	@Override
	public void onDisable() {
	}


	public static Main ExplosionPVPUtils() {
		return (Main) Bukkit.getServer().getPluginManager().getPlugin("ExplosionPVPUtils");
	}
	public void RunProtocalLib() {
		ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(this, ListenerPriority.NORMAL, PacketType.Login.Client.START) {
			public void onPacketReceiving(final PacketEvent e) {
				try {
					if (!e.getPlayer().hasPermission("ExplosionPVPUtils.Commands.Bypass")) {
						WrapperPlayClientTabComplete wpctc = new WrapperPlayClientTabComplete(e.getPacket());
						for (String cmd : (ArrayList<String>) getConfig().getStringList("ComandosBloqueados")) {
							if (wpctc.getText().toLowerCase().startsWith(cmd.toLowerCase() + " ") || wpctc.getText().equalsIgnoreCase(cmd)) {
								e.setCancelled(true);
								break;
							}
						}
					}
				} catch (Exception ex) {
				}
			}
		});
	}
}