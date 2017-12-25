private const val input = "1919\t2959\t82\t507\t3219\t239\t3494\t1440\t3107\t259\t3544\t683\t207\t562\t276\t2963\n" +
        "587\t878\t229\t2465\t2575\t1367\t2017\t154\t152\t157\t2420\t2480\t138\t2512\t2605\t876\n" +
        "744\t6916\t1853\t1044\t2831\t4797\t213\t4874\t187\t6051\t6086\t7768\t5571\t6203\t247\t285\n" +
        "1210\t1207\t1130\t116\t1141\t563\t1056\t155\t227\t1085\t697\t735\t192\t1236\t1065\t156\n" +
        "682\t883\t187\t307\t269\t673\t290\t693\t199\t132\t505\t206\t231\t200\t760\t612\n" +
        "1520\t95\t1664\t1256\t685\t1446\t253\t88\t92\t313\t754\t1402\t734\t716\t342\t107\n" +
        "146\t1169\t159\t3045\t163\t3192\t1543\t312\t161\t3504\t3346\t3231\t771\t3430\t3355\t3537\n" +
        "177\t2129\t3507\t3635\t2588\t3735\t3130\t980\t324\t266\t1130\t3753\t175\t229\t517\t3893\n" +
        "4532\t164\t191\t5169\t4960\t3349\t3784\t3130\t5348\t5036\t2110\t151\t5356\t193\t1380\t3580\n" +
        "2544\t3199\t3284\t3009\t3400\t953\t3344\t3513\t102\t1532\t161\t143\t2172\t2845\t136\t2092\n" +
        "194\t5189\t3610\t4019\t210\t256\t5178\t4485\t5815\t5329\t5457\t248\t5204\t4863\t5880\t3754\n" +
        "3140\t4431\t4534\t4782\t3043\t209\t216\t5209\t174\t161\t3313\t5046\t1160\t160\t4036\t111\n" +
        "2533\t140\t4383\t1581\t139\t141\t2151\t2104\t2753\t4524\t4712\t866\t3338\t2189\t116\t4677\n" +
        "1240\t45\t254\t1008\t1186\t306\t633\t1232\t1457\t808\t248\t1166\t775\t1418\t1175\t287\n" +
        "851\t132\t939\t1563\t539\t1351\t1147\t117\t1484\t100\t123\t490\t152\t798\t1476\t543\n" +
        "1158\t2832\t697\t113\t121\t397\t1508\t118\t2181\t2122\t809\t2917\t134\t2824\t3154\t2791"

fun main(args: Array<String>) {
    val spreadsheet = input.split("\n")
            .map { it.split("\t").map { it.toInt() } }
    val checksum = spreadsheet
            .map { it.max()!! - it.min()!! }
            .sum()
    println(checksum)

    val part2 = spreadsheet.map { evenlyDivision(it) }
            .sum()
    println(part2)
}

private fun evenlyDivision(list: List<Int>): Int {
    val sortedDescending = list.sortedDescending()
    sortedDescending.forEachIndexed { index, i ->
        sortedDescending.subList(index+1, list.size).forEach {
            if(i.evenlyDivides(it)) return i / it
        }
    }
    return -1
}

private fun Int.evenlyDivides(n: Int) = this % n == 0