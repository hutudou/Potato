package com.example.administrator.potato.javabeen;

import java.util.List;

/**
 * 作者 Administrator
 * 时间 2019/1/25
 */

public class CityBeen {

    /**
     * city : [{"city":"成都","district":[{"district":"成都"},{"district":"龙泉驿"},{"district":"新都"},{"district":"温江"},{"district":"金堂"},{"district":"双流"},{"district":"郫县"},{"district":"大邑"},{"district":"蒲江"},{"district":"新津"},{"district":"都江堰"},{"district":"彭州"},{"district":"邛崃"},{"district":"崇州"}]},{"city":"攀枝花","district":[{"district":"攀枝花"},{"district":"仁和"},{"district":"米易"},{"district":"盐边"}]},{"city":"自贡","district":[{"district":"自贡"},{"district":"富顺"},{"district":"荣县"}]},{"city":"绵阳","district":[{"district":"绵阳"},{"district":"三台"},{"district":"盐亭"},{"district":"安县"},{"district":"梓潼"},{"district":"北川"},{"district":"平武"},{"district":"江油"}]},{"city":"南充","district":[{"district":"南充"},{"district":"南部"},{"district":"营山"},{"district":"蓬安"},{"district":"仪陇"},{"district":"西充"},{"district":"阆中"}]},{"city":"达州","district":[{"district":"达州"},{"district":"宣汉"},{"district":"开江"},{"district":"大竹"},{"district":"渠县"},{"district":"万源"},{"district":"通川"},{"district":"达县"}]},{"city":"遂宁","district":[{"district":"遂宁"},{"district":"蓬溪"},{"district":"射洪"}]},{"city":"广安","district":[{"district":"广安"},{"district":"岳池"},{"district":"武胜"},{"district":"邻水"},{"district":"华蓥"}]},{"city":"巴中","district":[{"district":"巴中"},{"district":"通江"},{"district":"南江"},{"district":"平昌"}]},{"city":"泸州","district":[{"district":"泸州"},{"district":"泸县"},{"district":"合江"},{"district":"叙永"},{"district":"古蔺"},{"district":"纳溪"}]},{"city":"宜宾","district":[{"district":"宜宾"},{"district":"宜宾县"},{"district":"南溪"},{"district":"江安"},{"district":"长宁"},{"district":"高县"},{"district":"珙县"},{"district":"筠连"},{"district":"兴文"},{"district":"屏山"}]},{"city":"内江","district":[{"district":"内江"},{"district":"东兴"},{"district":"威远"},{"district":"资中"},{"district":"隆昌"}]},{"city":"资阳","district":[{"district":"资阳"},{"district":"安岳"},{"district":"乐至"},{"district":"简阳"}]},{"city":"乐山","district":[{"district":"乐山"},{"district":"犍为"},{"district":"井研"},{"district":"夹江"},{"district":"沐川"},{"district":"峨边"},{"district":"马边"},{"district":"峨眉"},{"district":"峨眉山"}]},{"city":"眉山","district":[{"district":"眉山"},{"district":"仁寿"},{"district":"彭山"},{"district":"洪雅"},{"district":"丹棱"},{"district":"青神"}]},{"city":"凉山","district":[{"district":"凉山"},{"district":"木里"},{"district":"盐源"},{"district":"德昌"},{"district":"会理"},{"district":"会东"},{"district":"宁南"},{"district":"普格"},{"district":"西昌"},{"district":"金阳"},{"district":"昭觉"},{"district":"喜德"},{"district":"冕宁"},{"district":"越西"},{"district":"甘洛"},{"district":"雷波"},{"district":"美姑"},{"district":"布拖"}]},{"city":"雅安","district":[{"district":"雅安"},{"district":"名山"},{"district":"荥经"},{"district":"汉源"},{"district":"石棉"},{"district":"天全"},{"district":"芦山"},{"district":"宝兴"}]},{"city":"甘孜","district":[{"district":"甘孜"},{"district":"康定"},{"district":"泸定"},{"district":"丹巴"},{"district":"九龙"},{"district":"雅江"},{"district":"道孚"},{"district":"炉霍"},{"district":"新龙"},{"district":"德格"},{"district":"白玉"},{"district":"石渠"},{"district":"色达"},{"district":"理塘"},{"district":"巴塘"},{"district":"乡城"},{"district":"稻城"},{"district":"得荣"}]},{"city":"阿坝","district":[{"district":"阿坝"},{"district":"汶川"},{"district":"理县"},{"district":"茂县"},{"district":"松潘"},{"district":"九寨沟"},{"district":"金川"},{"district":"小金"},{"district":"黑水"},{"district":"马尔康"},{"district":"壤塘"},{"district":"若尔盖"},{"district":"红原"}]},{"city":"德阳","district":[{"district":"德阳"},{"district":"中江"},{"district":"广汉"},{"district":"什邡"},{"district":"绵竹"},{"district":"罗江"}]},{"city":"广元","district":[{"district":"广元"},{"district":"旺苍"},{"district":"青川"},{"district":"剑阁"},{"district":"苍溪"}]}]
     * province : 四川
     */

    private String province;
    private List<CityBean> city;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public List<CityBean> getCity() {
        return city;
    }

    public void setCity(List<CityBean> city) {
        this.city = city;
    }

    public static class CityBean {
        /**
         * city : 成都
         * district : [{"district":"成都"},{"district":"龙泉驿"},{"district":"新都"},{"district":"温江"},{"district":"金堂"},{"district":"双流"},{"district":"郫县"},{"district":"大邑"},{"district":"蒲江"},{"district":"新津"},{"district":"都江堰"},{"district":"彭州"},{"district":"邛崃"},{"district":"崇州"}]
         */

        private String city;
        private List<DistrictBean> district;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public List<DistrictBean> getDistrict() {
            return district;
        }

        public void setDistrict(List<DistrictBean> district) {
            this.district = district;
        }

        public static class DistrictBean {
            /**
             * district : 成都
             */

            private String district;

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }
        }
    }
}
