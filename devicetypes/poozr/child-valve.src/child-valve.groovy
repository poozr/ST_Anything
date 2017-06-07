**
 *  Child Valve
 *
 *  Copyright 2017 Rob Rhodes
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 *  Change History:
 *
 *    Date        Who            What
 *    ----        ---            ----
 *    2017-04-10  Dan Ogorchock  Original Creation
 *    2017-06-06  Prof. Mayhem   Reworked for Water Valve Control
 *    2017-06-06  Rob Rhodes     Reworked to simple Valve Control
 *
 * 
 */
metadata {
	definition (name: "Child Valve", namespace: "poozr", author: "Rob Rhodes") {
		capability "Valve"
		capability "Switch"
		capability "Relay Switch"
		capability "Actuator"
		capability "Sensor"
	}

	tiles(scale: 2) {
		multiAttributeTile(name:"valve", type: "generic", width: 3, height: 4, canChangeIcon: true){
			tileAttribute ("device.valve", key: "PRIMARY_CONTROL") {
				attributeState "closed", label: '${name}', action: "valve.open", icon: "st.valves.water.closed", backgroundColor: "#ffffff", nextState:"Opening"
				attributeState "open", label: '${name}', action: "valve.close", icon: "st.valves.water.open", backgroundColor: "#00A0DC", nextState:"Closing"
				attributeState "Opening", label:'${name}', action:"valve.close", icon:"st.valves.water.open", backgroundColor:"#00A0DC", nextState:"Closing"
				attributeState "Closing", label:'${name}', action:"valve.open", icon:"st.valves.water.closed", backgroundColor:"#ffffff", nextState:"Opening"
			}
		}
	}
}

void open() {
	parent.childOpen(device.deviceNetworkId)
}

void close() {
	parent.childClose(device.deviceNetworkId)
}
