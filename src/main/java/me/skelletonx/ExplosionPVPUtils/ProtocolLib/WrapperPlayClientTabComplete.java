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
package me.skelletonx.ExplosionPVPUtils.ProtocolLib;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;


/**
 * Sent when the user presses [tab] while writing text.
 * @author Kristian
 */
public class WrapperPlayClientTabComplete extends AbstractPacket {
    public static final PacketType TYPE = PacketType.Play.Client.TAB_COMPLETE;
    
    public WrapperPlayClientTabComplete() {
        super(new PacketContainer(TYPE), TYPE);
        handle.getModifier().writeDefaults();
    }
    
    public WrapperPlayClientTabComplete(PacketContainer packet) {
        super(packet, TYPE);
    }
    
    /**
     * Retrieve all the text currently behind the cursor. 
     * @return The current Text
    */
    public String getText() {
        return handle.getStrings().read(0);
    }
    
    /**
     * Set all the text currently behind the cursor. 
     * @param value - new value.
    */
    public void setText(String value) {
        handle.getStrings().write(0, value);
    }   
}

