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
package me.skelletonx.ExplosionPVPUtils.Disable;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.skelletonx.ExplosionPVPUtils.*;


public class DisableListeners implements Listener {
	
	Main ExplosionPVPUltis = Main.ExplosionPVPUtils();
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPreprocess(PlayerCommandPreprocessEvent e) {
		for (String cmd : ExplosionPVPUltis.getConfig().getStringList("ComandosBloqueados")) {
			if (!e.getPlayer().hasPermission("ExplosionPVPUtils.Commands.Bypass")) {
				if (e.getMessage().toLowerCase().startsWith(cmd + " ") || e.getMessage().toLowerCase().equals(cmd)) {
					e.setCancelled(true);
					e.getPlayer().sendMessage(ExplosionPVPUltis.getConfig().getString("Mensagens.No_Perm").replace("&", "§"));
					break;
				}
			}
		}
	}
    @EventHandler (priority = EventPriority.LOWEST)
    public void onLeaveFall(LeavesDecayEvent e){
        e.setCancelled(true);
    }
    @EventHandler
    public  void onPlayerQuit(PlayerQuitEvent e){
        e.setQuitMessage(null);
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        e.setJoinMessage(null);
        if (p.hasPermission("ExplosionPVPUtils.AdminJoin")){
            e.setJoinMessage("§c§lSTAFF§b "+p.getName()+" §6Entrou no servidor!");
        }

    }
    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent e){
    	e.setDeathMessage(null);
		Player p = e.getEntity();
		if(p.hasPermission("ExplosionPVPUtils.AdminMorte")){
            e.getDrops().clear();
            p.sendMessage(ExplosionPVPUltis.getConfig().getString("Mensagens.Staff_Morte").replace("&", "§"));
			return;
			
		}
    }
    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        e.setQuitMessage(null);
    }

}
