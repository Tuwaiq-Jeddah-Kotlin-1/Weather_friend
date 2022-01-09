package com.example.weather_friend1

    import android.content.Context
    import android.content.SharedPreferences
    import android.provider.Settings.Global.getString
    import androidx.fragment.app.FragmentPagerAdapter
    import androidx.fragment.app.Fragment
    import androidx.fragment.app.FragmentManager
    import com.example.weather_friend1.ui.CityUserFragment
    import com.example.weather_friend1.ui.SearchCityFragment
    import com.google.rpc.context.AttributeContext

class PageAdapterCitesEdit(val context: Context,fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
            return 2;
        }

        override fun getItem(position: Int): Fragment {

            when(position) {
                0 -> {
                    return CityUserFragment()
                }
                1 -> {
                    return SearchCityFragment()
                }

                else -> {
                    return CityUserFragment()
                }
            }

        }

        override fun getPageTitle(position: Int): CharSequence? {
            when (position) {
                0 -> {


                    return context.getString(R.string.listUser)

                }
                1 -> {
                    return context.getString(R.string.searChCity)
                }

            }
            return super.getPageTitle(position)
        }

    }


