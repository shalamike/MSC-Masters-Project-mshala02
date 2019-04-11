/**
 * Created by mshala02 on 10/04/2019.
 */

import kotlin.*
import kotlin.math.*



class vector_position_and_motion {


    //change in time
    var d_time = 1

    // initial position
    var i_pos_init = 200
    var j_pos_init = 200

    // initiallising velocity
    var i_pos = 0
    var j_pos = 0

    //initial velocity
    var i_vel = 30
    var j_vel = 40

    var i_vel_init = 30
    var j_vel_init = 40

    //initial acceleration
    var i_acc_init = 4
    var j_acc_init = 3

    // basic particle properties
    var p_mass = 4
    var i_force = 5

    var j_force1 = 0
    var j_force2 = 0


    var gravity = 9.81;

    // vel_resultant

    fun simulat_motion()
    {
        //simulate change in position (displacement)
        i_pos += i_pos_init + i_vel * d_time
        j_pos +=j_pos_init +  j_vel * d_time


        i_vel += i_acc * d_time;
        j_vel += j_acc * d_time;

        i_acc = i_force/p_mass

        j_acc = (j_force1/p_mass) - (gravity/p_mass)


        fun sqrt():
    }


}