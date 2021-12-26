package com.example.weather_friend1

    import androidx.fragment.app.FragmentPagerAdapter
    import androidx.fragment.app.Fragment
    import androidx.fragment.app.FragmentManager
    import com.example.weather_friend1.ui.CityUserFragment
    import com.example.weather_friend1.ui.SearchCityFragment

class PageAdapterCitesEdit(fm: FragmentManager) : FragmentPagerAdapter(fm) {
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
                    return "Your Cites"
                }
                1 -> {
                    return "Search New City"
                }

            }
            return super.getPageTitle(position)
        }
    }


