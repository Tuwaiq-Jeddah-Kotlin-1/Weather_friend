package com.example.weather_friend1

    import android.content.Context
    import android.content.SharedPreferences
    import androidx.fragment.app.FragmentPagerAdapter
    import androidx.fragment.app.Fragment
    import androidx.fragment.app.FragmentManager
    import com.example.weather_friend1.ui.CityUserFragment
    import com.example.weather_friend1.ui.SearchCityFragment
    import com.google.rpc.context.AttributeContext

class PageAdapterCitesEdit(context: Context,fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private var preferences: SharedPreferences = context.getSharedPreferences("sp", Context.MODE_PRIVATE)

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

                    return "Your city"

                }
                1 -> {
                    return R.string.SearchCity.toString()
                }

            }
            return super.getPageTitle(position)
        }
    }


