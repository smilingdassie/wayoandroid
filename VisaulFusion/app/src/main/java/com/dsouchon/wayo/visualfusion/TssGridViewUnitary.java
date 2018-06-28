package com.dsouchon.wayo.visualfusion;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.dsouchon.wayo.visualfusion.R;

import java.util.ArrayList;


public class TssGridViewUnitary extends  ArrayAdapter <AndroidStoreUnit> {

    Context context;
    private ArrayList<AndroidStoreUnit> data = new ArrayList<AndroidStoreUnit>();



    public TssGridViewUnitary(Context context, ArrayList<AndroidStoreUnit> data)
    {
        super(context, 0);
        this.context=context;
        this.data = data;
    }

    @Override
    public int getCount()
    {
        return data.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(R.layout.tss_row_unitary, parent, false);
        }
        else{
            row = (View) convertView;
        }


            TextView ItemTypeName = (TextView) row.findViewById(R.id.textView);
            ImageView ImageBase64 = (ImageView) row.findViewById(R.id.imageView);
            TextView MaxQuantityFromMatrix = (TextView) row.findViewById(R.id.textViewsub);



            AndroidStoreUnit item = data.get(position);


            ItemTypeName.setText(item.getItemTypeName());//"Display Item 1002");
            ImageBase64.setImageResource(getImageFromDrawables(item.getImagePath()));//R.drawable.dis1);//TODO

            Integer max = item.getMaxQuantityFromMatrix();
            MaxQuantityFromMatrix.setText(max.toString());



        return row;

    }

    public int getImageFromDrawables(String imageName)
    {
        int returnThis = 0;
        switch(imageName)
        {
            case "Illuminated_Modrail.jpg" : returnThis = R.drawable.illuminated_modrail; break;
            case "Modular_Illuminated_Wall_Panel_pack_of_3.jpg" : returnThis = R.drawable.modular_illuminated_wall_panel_pack_of_3; break;
            case "Brand_Pillars.jpg" : returnThis = R.drawable.brand_pillars; break;
            case "Table_Defenders.jpg" : returnThis = R.drawable.table_defenders; break;
            case "TV_Wall_Essential.jpg" : returnThis = R.drawable.tv_wall_essential; break;
            case "TV_Wall_Premium.jpg" : returnThis = R.drawable.tv_wall_premium; break;
            case "Back_Of_Bar_T1.jpg" : returnThis = R.drawable.back_of_bar_t1; break;
            case "Back_Of_Bar_T2.jpg" : returnThis = R.drawable.back_of_bar_t2; break;
            case "Back_Of_Bar_ESS.jpg" : returnThis = R.drawable.back_of_bar_ess; break;
            case "Entrance_Canopy.jpg" : returnThis = R.drawable.entrance_canopy; break;
            case "Mini_Pylon.jpg" : returnThis = R.drawable.mini_pylon; break;
            case "Outlet_Sign.jpg" : returnThis = R.drawable.outlet_sign; break;
            case "Conversation_Table.jpg" : returnThis = R.drawable.conversation_table; break;
            case "Wall_Branding_Graphic_Panels.jpg" : returnThis = R.drawable.wall_branding_graphic_panels; break;
//Tusker
            case "illuminated_modrail_tusker.png" : returnThis = R.drawable.illuminated_modrail_tusker; break;
            case "brand_pillars_tusker.png" : returnThis = R.drawable.brand_pillars_tusker; break;
            case "tv_wall_essential_tusker.png" : returnThis = R.drawable.tv_wall_essential_tusker; break;
            case "tv_wall_premium_tusker.png" : returnThis = R.drawable.tv_wall_premium_tusker; break;
            case "table_defender_tusker.png" : returnThis = R.drawable.table_defender_tusker; break;
            case "outlet_sign_tusker.png" : returnThis = R.drawable.outlet_sign_tusker; break;
            case "conversation_table_tusker.png" : returnThis = R.drawable.conversation_table_tusker; break;
            case "wall_branding_graphic_panels_tusker.png" : returnThis = R.drawable.wall_branding_graphic_panels_tusker; break;
            case "communal_bench_tusker.png" : returnThis = R.drawable.communal_bench_tusker; break;
            case "back_of_bar_tier_1_tusker.png" : returnThis = R.drawable.back_of_bar_tier_1_tusker; break;
            case "back_of_bar_tier_2_tusker.png" : returnThis = R.drawable.back_of_bar_tier_2_tusker; break;
            case "back_of_bar_essentials_tusker.png" : returnThis = R.drawable.back_of_bar_essentials_tusker; break;
            case "modular_illuminated_wall_panel_tier_1_tusker.png" : returnThis = R.drawable.modular_illuminated_wall_panel_tier_1_tusker; break;
            case "modular_illuminated_wall_panel_tier_2_tusker.png" : returnThis = R.drawable.modular_illuminated_wall_panel_tier_2_tusker; break;
            case "modular_illuminated_wall_panel_essentials_tusker.png" : returnThis = R.drawable.modular_illuminated_wall_panel_essentials_tusker; break;
        //Guinness
            case "outside_signage_guinness.png" : returnThis = R.drawable.outside_signage_guinness; break;
            case "solar_light_box_guinness.png" : returnThis = R.drawable.solar_light_box_guinness; break;
            case "uhi_board_guinness.png" : returnThis = R.drawable.uhi_board_guinness; break;
            case "front_bar_signage_guinness.png" : returnThis = R.drawable.front_bar_signage_guinness; break;
            case "table_talker_guinness.png" : returnThis = R.drawable.table_talker_guinness; break;
            case "tv_cladding_guinness.png" : returnThis = R.drawable.tv_cladding_guinness; break;
            case "couch_guinness.png" : returnThis = R.drawable.couch_guinness; break;
            case "bench_1_guinness.png" : returnThis = R.drawable.bench_1_guinness; break;
            case "bench_2_guinness.png" : returnThis = R.drawable.bench_2_guinness; break;
            case "modular_back_of_bar_essentials_guinness.png" : returnThis = R.drawable.modular_back_of_bar_essentials_guinness; break;
            case "modular_back_of_bar_tier_1_guinness.png" : returnThis = R.drawable.modular_back_of_bar_tier_1_guinness; break;
            case "modular_back_of_bar_tier_2_guinness.png" : returnThis = R.drawable.modular_back_of_bar_tier_2_guinness; break;
            case "wall_branding_graphic_panels_essentials_guinness.png" : returnThis = R.drawable.wall_branding_graphic_panels_essentials_guinness; break;
            case "wall_branding_graphic_panels_tier_1_guinness.png" : returnThis = R.drawable.wall_branding_graphic_panels_tier_1_guinness; break;
            case "wall_branding_graphic_panels_tier_2_guinness.png" : returnThis = R.drawable.wall_branding_graphic_panels_tier_2_guinness; break;
            case "wall_branding_essentials_guinness.png" : returnThis = R.drawable.wall_branding_essentials_guinness; break;
            case "wall_branding_tier_1_guinness.png" : returnThis = R.drawable.wall_branding_tier_1_guinness; break;
            case "wall_branding_tier_2_guinness.png" : returnThis = R.drawable.wall_branding_tier_2_guinness; break;
            //Kenya Cane
            case "uhi_board_kenya_cane.png" : returnThis = R.drawable.uhi_board_kenya_cane; break;
            case "wall_mirrors_kenya_cane.png" : returnThis = R.drawable.wall_mirrors_kenya_cane; break;
            case "light_box_kenya_cane.png" : returnThis = R.drawable.light_box_kenya_cane; break;
            case "wall_branding_graphic_panels_kenya_cane.png" : returnThis = R.drawable.wall_branding_graphic_panels_kenya_cane; break;
            case "table_defender_kenya_cane.png" : returnThis = R.drawable.table_defender_kenya_cane; break;
            case "tv_cladding_kenya_cane.png" : returnThis = R.drawable.tv_cladding_kenya_cane; break;
            case "modular_back_of_bar_unit_header_lightbox_kenya_cane.png" : returnThis = R.drawable.modular_back_of_bar_unit_header_lightbox_kenya_cane; break;
            case "cafe_table_with_seating_kenya_cane.png" : returnThis = R.drawable.cafe_table_with_seating_kenya_cane; break;
            case "perfect_serve_kenya_cane.png" : returnThis = R.drawable.perfect_serve_kenya_cane; break;
            case "outlet_name_kenya_cane.png" : returnThis = R.drawable.outlet_name_kenya_cane; break;
            case "wall_counter_kenya_cane.png" : returnThis = R.drawable.wall_counter_kenya_cane; break;
            case "bench_kenya_cane.png" : returnThis = R.drawable.bench_kenya_cane; break;
            case "cocktail_table_with_bar_stool_kenya_cane.png" : returnThis = R.drawable.cocktail_table_with_bar_stool_kenya_cane; break;


            case "guinness_Canopy.jpg" : returnThis = R.drawable.guinness_canopy; break;
            case "guinness_Outside_Signage.jpg" : returnThis = R.drawable.guinness_outside_signage; break;
            case "guinness_Solar_Light_Box.jpg" : returnThis = R.drawable.guinness_solar_light_box; break;
            case "guinness_UHI_Board.jpg" : returnThis = R.drawable.guinness_uhi_board; break;
            case "guinness_wall_branding_modrail.jpg" : returnThis = R.drawable.guinness_wall_branding_modrail; break;
            case "guinness_Wall_Branding_Graphic_Panels.jpg" : returnThis = R.drawable.guinness_wall_branding_graphic_panels; break;
            case "guinness_Front_Bar_Signage.jpg" : returnThis = R.drawable.guinness_front_bar_signage; break;
            case "guinness_Modular_Back_of_bar_Tier_1.jpg" : returnThis = R.drawable.guinness_modular_back_of_bar_tier_1; break;
            case "guinness_Modular_Back_of_bar_Tier_2.jpg" : returnThis = R.drawable.guinness_modular_back_of_bar_tier_2; break;
            case "guinness_Modular_Back_of_bar_Essentials.jpg" : returnThis = R.drawable.guinness_modular_back_of_bar_tier_essentials; break;
            case "guinness_Table_Talker.jpg" : returnThis = R.drawable.guinness_table_defenders; break;
            case "guinness_TV_Cladding.jpg" : returnThis = R.drawable.guinness_tv_cladding; break;
            case "guinness_Projector_Wall.jpg" : returnThis = R.drawable.guinness_projector_wall; break;
            case "guinness_Couch.jpg" : returnThis = R.drawable.guinness_couch; break;
            case "guinness_Bench_1.jpg" : returnThis = R.drawable.guinness_bench_1; break;
            case "guinness_Bench_2.jpg" : returnThis = R.drawable.guinness_bench2; break;
            case "kenya_cane_uhi_board_chalkboard.jpg" : returnThis = R.drawable.kenya_cane_uhi_board_chalkboard; break;
            case "kenya_cane_Wall_Mirrors.jpg" : returnThis = R.drawable.kenya_cane_wall_mirrors; break;
            case "kenya_cane_Light_Box.jpg" : returnThis = R.drawable.kenya_cane_light_box; break;
            case "kenya_cane_Wall_Branding_Graphic_Panels.jpg" : returnThis = R.drawable.kenya_cane_wall_branding_graphic_panels; break;
            case "kenya_cane_Table_Talkers.jpg" : returnThis = R.drawable.kenya_cane_table_defender; break;
            case "kenya_cane_TV_Cladding.jpg" : returnThis = R.drawable.kenya_cane_tv_cladding; break;
            case "kenya_cane_modular_BOB_lightbox.jpg" : returnThis = R.drawable.kenya_cane_modular_bob_lightbox; break;
            case "kenya_cane_Café_Table_With_Seating.jpg" : returnThis = R.drawable.kenya_cane_cafe_table_with_seating; break;
            case "kenya_cane_Perfect_Serve.jpg" : returnThis = R.drawable.kenya_cane_perfect_serve; break;
            case "kenya_cane_Outlet_Name.jpg" : returnThis = R.drawable.kenya_cane_outlet_name; break;
            case "kenya_cane_walll_counter.jpg" : returnThis = R.drawable.kenya_cane_walll_counter; break;
            case "kenya_cane_Bench.jpg" : returnThis = R.drawable.kenya_cane_bench; break;
            case "kenya_cane_Cocktail_Table_With_Bar_Stool.jpg" : returnThis = R.drawable.kenya_cane_cocktail_table_with_bar_stool; break;
            //smirnoff
            case "outlet_sign_smirnoff.jpg" : returnThis = R.drawable.outlet_sign_smirnoff; break;
            case "modrail_smirnoff.jpg" : returnThis = R.drawable.modrail_smirnoff; break;
            case "snapframe_smirnoff.jpg" : returnThis = R.drawable.snapframe_smirnoff; break;
            case "wall_mirrors_smirnoff.jpg" : returnThis = R.drawable.wall_mirrors_smirnoff; break;
            case "wall_mounted_sign_smirnoff.jpg" : returnThis = R.drawable.wall_mounted_sign_smirnoff; break;
            case "double_sided_illuminated_sign_smirnoff.jpg" : returnThis = R.drawable.double_sided_illuminated_sign_smirnoff; break;
            case "dj_booth_cladding_smirnoff.jpg" : returnThis = R.drawable.dj_booth_cladding_smirnoff; break;
            case "cocktail_table_and_chairs_smirnoff.jpg" : returnThis = R.drawable.cocktail_table_and_chairs_smirnoff; break;
            case "conversation_table_smirnoff.jpg" : returnThis = R.drawable.conversation_table_smirnoff; break;
            case "wall_branding_graphic_panels_smirnoff.jpg" : returnThis = R.drawable.wall_branding_graphic_panels_smirnoff; break;
            case "modular_back_of_bar_tier_1_smirnoff.jpg" : returnThis = R.drawable.modular_back_of_bar_tier_1_smirnoff; break;
            case "modular_back_of_bar_tier_2_smirnoff.jpg" : returnThis = R.drawable.modular_back_of_bar_tier_2_smirnoff; break;
            case "modular_back_of_bar_essentials_smirnoff.jpg" : returnThis = R.drawable.modular_back_of_bar_essentials_smirnoff; break;
            //johnnie walker
            case "solar_snap_frame_johnnie_walker.jpg" : returnThis = R.drawable.solar_snap_frame_johnnie_walker; break;
            case "bar_front_johnnie_walker.jpg" : returnThis = R.drawable.bar_front_johnnie_walker; break;
            case "back_of_bar_johnnie_walker.jpg" : returnThis = R.drawable.back_of_bar_johnnie_walker; break;
            case "outdoor_coffee_table_johnnie_walker.jpg" : returnThis = R.drawable.outdoor_coffee_table_johnnie_walker; break;
            case "indoor_coffee_table_johnnie_walker.jpg" : returnThis = R.drawable.indoor_coffee_table_johnnie_walker; break;
            case "ottoman_johnnie_walker.jpg" : returnThis = R.drawable.ottoman_johnnie_walker; break;
            case "lounge_set_black_johnnie_walker.jpg" : returnThis = R.drawable.lounge_set_black_johnnie_walker; break;
            case "lounge_set_white_johnnie_walker.jpg" : returnThis = R.drawable.lounge_set_white_johnnie_walker; break;
            case "couch_johnnie_walker.jpg" : returnThis = R.drawable.couch_johnnie_walker; break;
            case "brand_pillars_johnnie_walker.jpg" : returnThis = R.drawable.brand_pillars_johnnie_walker; break;
            case "mobile_bar_johnnie_walker.jpg" : returnThis = R.drawable.mobile_bar_johnnie_walker; break;
            case "wall_branding_graphic_panels_johnnie_walker.jpg" : returnThis = R.drawable.wall_branding_graphic_panels_johnnie_walker; break;
            case "decorative_frames_johnnie_walker.jpg" : returnThis = R.drawable.decorative_frames_johnnie_walker; break;
            case "accessory_placeholder.png" : returnThis = R.drawable.decorative_frames_johnnie_walker; break;
            default: break;
        }

        return returnThis;
    }

}
