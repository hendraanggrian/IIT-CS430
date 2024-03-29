package com.example

import com.google.common.collect.Multimap
import com.google.common.collect.TreeMultimap

class DfsParser(val experimental: Boolean = false) : Parser() {
    override fun toString(): String = "DFS parser${if (experimental) " (exp.)" else ""}"

    override fun onParse(
        priceMap: Map<Int, Price>,
        promotionMultimap: Multimap<Double, Promotion>
    ): String {
        val sb = StringBuilder()
        val spent = dfs(priceMap, promotionMultimap.values().toList(), sb, 0)
        return sb.append(spent.formatted).toString()
    }

    private fun dfs(
        priceMap: Map<Int, Price>,
        promotionList: List<Promotion>,
        sb: StringBuilder,
        i: Int
    ): Double {
        var sum = priceMap.values.sumOf { it.worth }
        for (j in i until promotionList.size) {
            val promotion = promotionList[j]
            if (promotion.items.all { priceMap[it.id]!!.amount >= it.amount }) {
                // use promotion
                promotion.items.forEach { priceMap[it.id]!!.amount -= it.amount }
                if (experimental) {
                    sb.appendLine(promotion.toString())
                }
                sum = minOf(sum, promotion.price + dfs(priceMap, promotionList, sb, i))
                // backtrack
                promotion.items.forEach { priceMap[it.id]!!.amount += it.amount }
            }
        }
        if (experimental) {
            priceMap.forEach { (_, price) ->
                if (price.amount > 0) {
                    sb.appendLine(price.toString())
                }
            }
        }
        return sum
    }
}

class GreedyParser : Parser() {
    override fun toString(): String = "Greedy parser"

    override fun onParse(
        priceMap: Map<Int, Price>,
        promotionMultimap: Multimap<Double, Promotion>
    ): String {
        val sb = StringBuilder()
        var spent = 0.0
        // abuse promotions
        promotionMultimap.values().reversed().forEach { promotion ->
            while (promotion.items.all { priceMap[it.id]!!.amount - it.amount >= 0 }) {
                sb.appendLine(promotion.toString())
                spent += promotion.price
                promotion.items.forEach { priceMap[it.id]!!.amount -= it.amount }
            }
        }
        // deduce leftover with non-promotions
        priceMap.values.forEach { item ->
            if (item.amount > 0) {
                sb.appendLine(item.toString())
                spent += item.worth
                item.amount = 0
            }
        }
        return sb.append(spent.formatted).toString()
    }
}

abstract class Parser {
    abstract fun onParse(
        priceMap: Map<Int, Price>,
        promotionMultimap: Multimap<Double, Promotion>
    ): String

    fun parse(sample: Sample): String = parse(sample.prices, sample.promotions)

    fun parse(prices: String, promotions: String): String {
        check(prices.isNotBlank()) { "Empty input." }
        check(promotions.isNotBlank()) { "Empty promotions." }

        val priceMap = mutableMapOf<Int, Price>()
        val promotionMultimap = TreeMultimap.create<Double, Promotion>()
        prices.forEachLine {
            val parts = it.split(' ')
            check(parts.size == 3) { "Invalid price for '$it'" }
            priceMap += parts[0].toInt() to Price(
                parts[0].toInt(),
                parts[1].toInt(),
                parts[2].toDouble()
            )
        }
        promotions.forEachLine { s ->
            val parts = s.split(' ')
            check(parts.size >= 4) { "Invalid promotion for '$s'" }
            val pairCount = parts.first().toInt()
            val items = mutableListOf<Promotion.Item>()
            var id: Int? = null
            parts.drop(1).take(pairCount * 2).forEach {
                if (id != null) {
                    items += Promotion.Item(id!!, it.toInt(), priceMap[id]!!.price)
                    id = null
                } else {
                    id = it.toInt()
                }
            }
            val price = parts.last().toDouble()
            val saving = items.sumOf { it.worth } - price
            promotionMultimap.put(saving, Promotion(items, price))
        }
        return onParse(priceMap, promotionMultimap)
    }

    private fun String.forEachLine(action: (String) -> Unit) {
        val itemCount = lines().first().trimStart().toInt()
        lines().drop(1).take(itemCount).forEach { action(it.trim()) }
    }
}
