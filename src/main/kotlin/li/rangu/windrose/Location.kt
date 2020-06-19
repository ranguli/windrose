package li.rangu.windrose

import kotlin.math.roundToInt
import org.bukkit.entity.Player

object Location {
    fun getPlayerCoordinates(player: Player): Coordinates {
        val x = player.location.x.roundToInt()
        val y = player.location.y.roundToInt()
        val z = player.location.z.roundToInt()

        return Coordinates(x, y, z)

    }

}
