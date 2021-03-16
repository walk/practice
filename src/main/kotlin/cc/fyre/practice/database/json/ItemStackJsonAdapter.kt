package cc.fyre.practice.database.json

import cc.fyre.practice.Practice
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.*
import org.bukkit.potion.PotionEffect
import java.lang.reflect.Type

/**
 * @author Brew
 *
 * @date 3/4/2021
 * @project practice
 */
class ItemStackJsonAdapter : JsonDeserializer<ItemStack>, JsonSerializer<ItemStack> {

    private val token = object : TypeToken<List<String>>(){}

    override fun deserialize(element: JsonElement, type: Type, context: JsonDeserializationContext): ItemStack {


        val jsonObject = element.asJsonObject
        val toReturn = ItemStack(Material.valueOf(jsonObject["type"].asString),jsonObject["amount"].asInt,jsonObject["durability"].asShort)

        val itemMeta = toReturn.itemMeta

        if (jsonObject.has("meta")) {

            val meta = jsonObject["meta"].asJsonObject

            if (meta.has("lore")) {
                itemMeta.lore = Practice.instance.gson.fromJson<List<String>>(meta["lore"].asString,this.token.type)
            }

            if (meta.has("displayName")) {
                itemMeta.displayName = meta["displayName"].asString
            }

            when (itemMeta) {
                is BookMeta -> {

                    if (meta.has("title")) {
                        itemMeta.title = meta["title"].asString
                    }

                    if (meta.has("pages")) {
                        itemMeta.pages = meta["pages"].asJsonArray.map{it.asString}
                    }

                    if (meta.has("author")) {
                        itemMeta.author = meta["author"].asString
                    }

                }
                is MapMeta -> itemMeta.isScaling = meta["scaling"].asBoolean
                is SkullMeta -> if (meta.has("owner")) {
                    itemMeta.owner = meta["owner"].asString
                }
                is LeatherArmorMeta -> itemMeta.color = Color.fromRGB(meta["color"].asInt)
                is PotionMeta -> if (meta.has("effects")) {
                    meta["effects"].asJsonArray.forEach{itemMeta.addCustomEffect(Practice.instance.gson.fromJson(it.asJsonObject,
                        PotionEffect::class.java),true)}
                }
            }

        }

        toReturn.itemMeta = itemMeta

        if (jsonObject.has("enchantments")) {
            toReturn.addUnsafeEnchantments(jsonObject["enchantments"].asJsonObject.entrySet().associate{ Enchantment.getByName(it.key) to it.value.asInt})
        }

        return toReturn
    }

    override fun serialize(itemStack: ItemStack, type: Type, context: JsonSerializationContext): JsonElement {

        val toReturn = JsonObject()

        toReturn.addProperty("type",itemStack.type.name)

        if (itemStack.hasItemMeta()) {

            val meta = JsonObject()

            if (itemStack.itemMeta.hasLore()) {
                meta.addProperty("lore",Practice.instance.gson.toJson(itemStack.itemMeta.lore))
            }

            if (itemStack.itemMeta.hasDisplayName()) {
                meta.addProperty("displayName",itemStack.itemMeta.displayName)
            }

            when (itemStack.itemMeta) {
                is LeatherArmorMeta -> {
                    meta.addProperty("color",(itemStack.itemMeta as LeatherArmorMeta).color.asRGB())
                }
                is MapMeta -> {
                    meta.addProperty("scaling", (itemStack.itemMeta as MapMeta).isScaling)
                }
                is PotionMeta -> {

                    if ((itemStack.itemMeta as PotionMeta).customEffects.isNotEmpty()) {
                        meta.addProperty("effects",Practice.instance.gson.toJson((itemStack.itemMeta as PotionMeta).customEffects.map{Practice.instance.gson.toJson(it)}))
                    }

                }
                is SkullMeta -> {

                    if ((itemStack.itemMeta as SkullMeta).owner != null) {
                        meta.addProperty("owner",(itemStack.itemMeta as SkullMeta).owner)
                    }

                }
                is BookMeta -> {

                    val itemMeta = (itemStack.itemMeta as BookMeta)

                    if (itemMeta.hasTitle()) {
                        meta.addProperty("title",(itemStack.itemMeta as BookMeta).title)
                    }

                    if (itemMeta.hasPages()) {
                        meta.addProperty("pages",Practice.instance.gson.toJson((itemStack.itemMeta as BookMeta).pages))
                    }

                    if (itemMeta.hasAuthor()) {
                        meta.addProperty("author",(itemStack.itemMeta as BookMeta).author)
                    }

                }
            }

            toReturn.add("meta",meta)
        }

        toReturn.addProperty("amount",itemStack.amount)
        toReturn.addProperty("durability",itemStack.durability)

        val enchantments = JsonObject()

        itemStack.enchantments.entries.forEach{enchantments.addProperty(it.key.name,it.value)}

        toReturn.add("enchantments",enchantments)

        return toReturn
    }

}