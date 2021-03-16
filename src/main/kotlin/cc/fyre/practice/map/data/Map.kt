package cc.fyre.practice.map.data

import cc.fyre.practice.Practice
import com.google.gson.annotations.SerializedName
import net.frozenorb.qlib.cuboid.Cuboid
import net.minecraft.util.org.apache.commons.io.FileUtils
import org.bukkit.Bukkit
import org.bukkit.Location
import java.io.File
import java.lang.IllegalStateException
import java.nio.file.Files
import java.nio.file.StandardCopyOption

/**
 * @author Brew
 *
 * @date 11/6/2020
 * @project Practice
 */
class Map(@SerializedName("_id")val id: String) {

    var cuboid: Cuboid? = null

    var teamOneSpawn: Location? = null
    var teamTwoSpawn: Location? = null

    @Throws(IllegalStateException::class)
    fun save() {

        val file = File("${Practice.instance.server.worldContainer.path}/${this.id}")

        if(this.cuboid == null || this.teamOneSpawn == null || this.teamTwoSpawn == null) {
            throw IllegalStateException("Make sure to fully setup this map before saving it (ie: cuboid, team one & two spawn).")
        }

        if(!file.exists()) {
            throw IllegalStateException("Couldn't find a world by the name of ${this.id}")
        }

        if(this.getContainer() != null) {
            throw IllegalStateException("This world already exists in the map directory.")
        }

        this.cuboid!!.world.save()

//        FileUtils.copyFile(file,Practice.instance.mapHandler.container)
        FileUtils.copyDirectory(file,File("${Practice.instance.mapHandler.container}/${this.id}"))

//        Files.copy(file.toPath(),Practice.instance.mapHandler.container.toPath(),StandardCopyOption.COPY_ATTRIBUTES)

        Bukkit.getServer().scheduler.runTaskAsynchronously(Practice.instance) {
            Practice.instance.mapHandler.update(this)
        }
    }

    fun getContainer(): File? {
        val file = File("${Practice.instance.mapHandler.container.path}/${this.id}")

        if(!file.exists()) {
            return null
        }

        return file
    }

}