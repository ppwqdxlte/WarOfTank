package bridge;
/**
 *
 * Bridge模式（双维度扩展）：接口树，实现树，接口树的子类持有实现root
 *
 * v1: 哥哥追妹妹，各种礼物继承抽象类礼物
 *
 * v2: 如果礼物分为温柔的礼物和狂野的礼物
 *          WarmGift WildGift
 *
 * v3:这时Flower应该分为
 *      WarmFlower WildFlower
 *      WarmBook WildBook
 *
 *      如果再有别的礼物，比如抽象类型：ToughGift ColdGift
 *      或者具体的某种实现：Ring Car
 *
 *      就会产生类的爆炸
 *      WarmCar ColdRing WildCar WildFlower ...
 *
 * v4:使用桥接模式：
 *      分离抽象与具体实现，让他们可以独自发展
 *      Gift -> WarmGift ColdGift WildGift
 *      GiftImpl -> Flower Ring Car
 *
 */