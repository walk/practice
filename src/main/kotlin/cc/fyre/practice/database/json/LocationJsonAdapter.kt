package cc.fyre.practice.database.json

import cc.fyre.practice.Practice
import com.google.gson.*
import org.bukkit.Location
import java.lang.reflect.Type

/**
 * @author Brew
 *
 * @date 3/4/2021
 * @project practice
 */
class LocationJsonAdapter : JsonSerializer<Location>, JsonDeserializer<Location> {

    override fun serialize(location: Location, type: Type, context: JsonSerializationContext): JsonElement {

        val toReturn = JsonObject()

        toReturn.addProperty("x",location.x)
        toReturn.addProperty("y",location.y)
        toReturn.addProperty("z",location.z)
        toReturn.addProperty("yaw",location.yaw)
        toReturn.addProperty("pitch",location.pitch)
        toReturn.addProperty("world",location.world.name)

        return toReturn
    }

    override fun deserialize(element: JsonElement, type: Type, context: JsonDeserializationContext): Location {

        val jsonObject = element.asJsonObject

        return Location(
            Practice.instance.server.getWorld(jsonObject["world"].asString),
            jsonObject["x"].asDouble,
            jsonObject["y"].asDouble,
            jsonObject["z"].asDouble,
            jsonObject["yaw"].asFloat,
            jsonObject["pitch"].asFloat
        )

    }
}