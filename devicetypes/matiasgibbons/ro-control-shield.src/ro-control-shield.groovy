/*
 *  RO Control Shield
 *
 *  Author: MATIAS GIBBONS
 *  Date: 2016-07-05
 *  Revision:
 */
 
metadata {
	definition (name: "RO Control Shield", namespace: "matiasgibbons", author: "MJG") {
		capability "Switch"
		attribute "switch", "string"
		command "on, off"
	}
            
	tiles (scale: 2) {
		standardTile("switch", "device.switch", width: 2, height: 2, canChangeIcon: true, canChangeBackground: true) {
			state "on", label: '${name}', action: "switch.off", icon: "st.Outdoor.outdoor16", backgroundColor: "#79b821"
			state "off", label: '${name}', action: "switch.on", icon: "st.Outdoor.outdoor16", backgroundColor: "#ffffff"
		}
        valueTile("valve_a", "device.switch", width: 2, height: 2) {
			state "on", label: 'Valve A: ${name}', icon: "st.Outdoor.outdoor16", backgroundColor: "#79b821"
			state "off", label: 'Valve A: ${name}', icon: "st.Outdoor.outdoor16", backgroundColor: "#ffffff"
		}
        valueTile("valve_b", "device.switch", width: 2, height: 2) {
			state "on", label: 'Valve B: ${name}', icon: "st.Outdoor.outdoor16", backgroundColor: "#79b821"
			state "off", label: 'Valve B: ${name}', icon: "st.Outdoor.outdoor16", backgroundColor: "#ffffff"
		}
        valueTile("valve_c", "device.switch", width: 2, height: 2) {
			state "on", label: 'Valve C: ${name}', icon: "st.Outdoor.outdoor16", backgroundColor: "#79b821"
			state "off", label: 'Valve C: ${name}', icon: "st.Outdoor.outdoor16", backgroundColor: "#ffffff"
		}
        
		main("switch")
        
		details(["switch", "valve_a", "valve_b", "valve_c"])
	}
	
    simulator {
        status "on":  "catchall: 0104 0000 01 01 0040 00 0A21 00 00 0000 0A 00 0A6F6E"
        status "off": "catchall: 0104 0000 01 01 0040 00 0A21 00 00 0000 0A 00 0A6F6666"

        // reply messages
        reply "raw 0x0 { 00 00 0a 0a 6f 6e }": "catchall: 0104 0000 01 01 0040 00 0A21 00 00 0000 0A 00 0A6F6E"
        reply "raw 0x0 { 00 00 0a 0a 6f 66 66 }": "catchall: 0104 0000 01 01 0040 00 0A21 00 00 0000 0A 00 0A6F6666"
    }
}

def parse (String description) {
	def value = zigbee.parse(description)?.text
	def name = value && value != "ping" ? "response" : null

	def result = createEvent(name: name, value: value)

	log.debug "Parse returned ${result?.descriptionText}"

	return result
}

def on () {
	log.debug "On!"
    zigbee.smartShield (text: "on").format()
}

def off () {
	log.debug "Off!"
	zigbee.smartShield (text: "off").format()
}

def hello () {
	log.debug "Hello World!"
	zigbee.smartShield (text: "hello").format()
}