package com.tanidev.core.utils

import com.tanidev.core.data.source.local.entity.DiseasEntity
import com.tanidev.core.data.source.local.entity.PlantEntity
import com.tanidev.core.data.source.local.entity.ProductEntity
import com.tanidev.core.data.source.remote.response.DiseaseResponse
import com.tanidev.core.data.source.remote.response.PlantResponse
import com.tanidev.core.data.source.remote.response.ProductResponse
import com.tanidev.core.domain.model.*

object DataMapper {
    fun mapPlantEntitiesToDomain(input: List<PlantEntity>) =
        input.map {
            Plant(
                plantId = it.plantId,
                plantName = it.plantName,
                content = if (it.content.isNullOrEmpty()) "" else it.content,
                light = if (it.light.isNullOrEmpty()) "" else it.light,
                createdDate = if (it.createdDate.isNullOrEmpty()) "" else it.createdDate,
                plantType = if (it.plantType.isNullOrEmpty()) "" else it.plantType,
                kalium = if (it.kalium.isNullOrEmpty()) "" else it.kalium,
                waterContent = if (it.waterContent.isNullOrEmpty()) "" else it.waterContent,
                humidity = if (it.humidity.isNullOrEmpty()) "" else it.humidity,
                sodium = if (it.sodium.isNullOrEmpty()) "" else it.sodium,
                potassium = if (it.potassium.isNullOrEmpty()) "" else it.potassium,
                temperature = if (it.temperature.isNullOrEmpty()) "" else it.temperature,
                diseaseId = if (it.diseaseId.isNullOrEmpty()) "" else it.diseaseId,
                updatedTimestamp = it.updatedTimestamp,
                viewTotal = it.viewTotal
            )
        }


    fun mapPlantResponseToEntities(input: List<PlantResponse>): List<PlantEntity> {
        val plantList = ArrayList<PlantEntity>()
        input.map {
            val plant = PlantEntity(
                plantId = it.plantId,
                plantName = it.plantName,
                content = if (it.content.isNullOrEmpty()) "" else it.content,
                light = if (it.light.isNullOrEmpty()) "" else it.light,
                createdDate = if (it.createdDate.isNullOrEmpty()) "" else it.createdDate,
                plantType = if (it.plantType.isNullOrEmpty()) "" else it.plantType,
                kalium = if (it.kalium.isNullOrEmpty()) "" else it.kalium,
                waterContent = if (it.waterContent.isNullOrEmpty()) "" else it.waterContent,
                humidity = if (it.humidity.isNullOrEmpty()) "" else it.humidity,
                sodium = if (it.sodium.isNullOrEmpty()) "" else it.sodium,
                potassium = if (it.potassium.isNullOrEmpty()) "" else it.potassium,
                temperature = if (it.temperature.isNullOrEmpty()) "" else it.temperature,
                diseaseId = if (it.diseaseId.isNullOrEmpty()) "" else it.diseaseId,
                updatedTimestamp = it.updatedTimestamp,
                viewTotal = it.viewTotal
            )
            plantList.add(plant)
        }
        return plantList
    }

    fun mapPlantEntityToDomain(input: PlantEntity) = Plant(
        plantId = input.plantId,
        plantName = input.plantName,
        content = if (input.content.isNullOrEmpty()) "" else input.content,
        light = if (input.light.isNullOrEmpty()) "" else input.light,
        createdDate = if (input.createdDate.isNullOrEmpty()) "" else input.createdDate,
        plantType = if (input.plantType.isNullOrEmpty()) "" else input.plantType,
        kalium = if (input.kalium.isNullOrEmpty()) "" else input.kalium,
        waterContent = if (input.waterContent.isNullOrEmpty()) "" else input.waterContent,
        humidity = if (input.humidity.isNullOrEmpty()) "" else input.humidity,
        sodium = if (input.sodium.isNullOrEmpty()) "" else input.sodium,
        potassium = if (input.potassium.isNullOrEmpty()) "" else input.potassium,
        temperature = if (input.temperature.isNullOrEmpty()) "" else input.temperature,
        diseaseId = if (input.diseaseId.isNullOrEmpty()) "" else input.diseaseId,
        updatedTimestamp = input.updatedTimestamp,
        viewTotal = input.viewTotal
    )

    fun mapDiseaseEntitiesToDomain(input: List<DiseasEntity>) =
        input.map {
            Disease(
                diseaseId = it.diseaseId,
                diseaseName = it.diseaseName,
                diseaseType = it.diseaseType,
                productId = it.productId,
                plantId = it.plantId,
                content = it.content,
                createdDate = it.createdDate,
                viewTotal = it.viewTotal
            )
        }

    fun mapDiseaseResponseToEntities(input: List<DiseaseResponse>): List<DiseasEntity> {
        val diseaseList = ArrayList<DiseasEntity>()
        input.map {
            val plant = DiseasEntity(
                diseaseId = it.diseaseId,
                diseaseName = it.diseaseName,
                diseaseType = it.diseaseType,
                productId = it.productId,
                plantId = it.plantId,
                content = if (it.content.isNullOrEmpty()) "" else it.content ,
                createdDate = if (it.createdDate.isNullOrEmpty()) "" else it.createdDate ,
                viewTotal = it.viewTotal
            )
            diseaseList.add(plant)
        }
        return diseaseList
    }

    fun mapDiseaseEntityToDomain(input: DiseasEntity) = Disease(
        diseaseId = input.diseaseId,
        diseaseName = input.diseaseName,
        diseaseType = input.diseaseType,
        productId = input.productId,
        plantId = input.plantId,
        content = input.content,
        createdDate = input.createdDate,
        viewTotal = input.viewTotal
    )

    fun mapProductEntitiesToDomain(input: List<ProductEntity>) =
        input.map {
            Product(
                productId = it.productId,
                productName = it.productName,
                createdDate = if (it.createdDate.isNullOrEmpty()) "" else it.createdDate,
                description = if (it.description.isNullOrEmpty()) "" else it.description,
                imageId = if (it.imageId == null) -1 else it.imageId,
                image = if (it.image.isNullOrEmpty()) "" else it.image,
                imageThumbNail = if (it.imageThumbNail.isNullOrEmpty()) "" else it.imageThumbNail,
                productType = if (it.productType.isNullOrEmpty()) "" else it.productType,
                content = if (it.content.isNullOrEmpty()) "" else it.content,
                viewTotal = it.viewTotal
            )
        }

    fun mapProductResponseToEntities(input: List<ProductResponse>): List<ProductEntity> {
        val productList = ArrayList<ProductEntity>()
        input.map {
            val product = ProductEntity(
                productId = it.productId,
                productName = it.productName,
                createdDate = if (it.createdDate.isNullOrEmpty()) "" else it.createdDate,
                description = if (it.description.isNullOrEmpty()) "" else it.description,
                imageId = if (it.imageId == null) -1 else it.imageId,
                image = if (it.image.isNullOrEmpty()) "" else it.image,
                imageThumbNail = if (it.imageThumbNail.isNullOrEmpty()) "" else it.imageThumbNail,
                productType = if (it.productType.isNullOrEmpty()) "" else it.productType,
                content = if (it.content.isNullOrEmpty()) "" else it.content,
                viewTotal = it.viewTotal
            )
            productList.add(product)
        }
        return productList
    }

    fun mapProductEntityToDomain(input: ProductEntity) = Product(
        productId = input.productId,
        productName = input.productName,
        createdDate = if (input.createdDate.isNullOrEmpty()) "" else input.createdDate,
        description = if (input.description.isNullOrEmpty()) "" else input.description,
        imageId = if (input.imageId == null) -1 else input.imageId,
        image = if (input.image.isNullOrEmpty()) "" else input.image,
        imageThumbNail = if (input.imageThumbNail.isNullOrEmpty()) "" else input.imageThumbNail,
        productType = if (input.productType.isNullOrEmpty()) "" else input.productType,
        content = if (input.content.isNullOrEmpty()) "" else input.content,
        viewTotal = input.viewTotal
    )

    fun mapPlantEntitiesToMostViewed(input: List<PlantEntity>): List<MostViewed> {
        val mostViewedList = ArrayList<MostViewed>()
        input.map {
            val mostViewed = MostViewed(
                mostViewedId = it.plantId,
                mostViewdName = it.plantName,
                mostViewedTotal = it.viewTotal,
                mostViewedType = "Plant"
            )
            mostViewedList.add(mostViewed)
        }
        return mostViewedList
    }

    fun mapDiseaseEntitiesToMostViewed(input: List<DiseasEntity>): List<MostViewed> {
        val mostViewedList = ArrayList<MostViewed>()
        input.map {
            val mostViewed = MostViewed(
                mostViewedId = it.diseaseId,
                mostViewdName = it.diseaseName,
                mostViewedTotal = it.viewTotal,
                mostViewedType = "Disease"
            )
            mostViewedList.add(mostViewed)
        }
        return mostViewedList
    }

    fun mapProductEntitiesToMostViewed(input: List<ProductEntity>): List<MostViewed> {
        val mostViewedList = ArrayList<MostViewed>()
        input.map {
            val mostViewed = MostViewed(
                mostViewedId = it.productId,
                mostViewdName = it.productName,
                mostViewedTotal = it.viewTotal,
                mostViewedType = "Product"
            )
            mostViewedList.add(mostViewed)
        }
        return mostViewedList
    }

    fun mapPlanEntitiesToSearchResult(input: List<PlantEntity>): List<SearchResult> {
        val searchResultList = ArrayList<SearchResult>()
        input.map {
            val searchResult = SearchResult(
                searchId = it.plantId,
                searchName = it.plantName,
                searchType = "Plant"
            )
            searchResultList.add(searchResult)
        }
        return searchResultList
    }

    fun mapDiseaseEntitiesToSearchResult(input: List<DiseasEntity>): List<SearchResult> {
        val searchResultList = ArrayList<SearchResult>()
        input.map {
            val searchResult = SearchResult(
                searchId = it.diseaseId,
                searchName = it.diseaseName,
                searchType = "Disease"
            )
            searchResultList.add(searchResult)
        }
        return searchResultList
    }

    fun mapProductEntitiesToSearchResult(input: List<ProductEntity>): List<SearchResult> {
        val searchResultList = ArrayList<SearchResult>()
        input.map {
            val searchResult = SearchResult(
                searchId = it.productId,
                searchName = it.productName,
                searchType = "Product"
            )
            searchResultList.add(searchResult)
        }
        return searchResultList
    }
}