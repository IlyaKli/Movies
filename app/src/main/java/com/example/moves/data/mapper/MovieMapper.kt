package com.example.moves.data.mapper


import com.example.moves.data.database.model.MovieDbModel
import com.example.moves.data.database.model.PosterDbModel
import com.example.moves.data.database.model.RatingDbModel
import com.example.moves.data.network.model.*
import com.example.moves.domain.model.*
import javax.inject.Inject

class MovieMapper @Inject constructor() {

    fun mapDbModelToEntity(dbModel: MovieDbModel) = Movie(
        id = dbModel.id,
        name = dbModel.name,
        description = dbModel.description,
        year = dbModel.year,
        poster = mapPosterDbToPosterEntity(dbModel.poster),
        rating = mapRatingDbToRatingEntity(dbModel.rating)
    )

    private fun mapPosterDbToPosterEntity(posterDbModel: PosterDbModel) = Poster(
        url = posterDbModel.url
    )

    private fun mapRatingDbToRatingEntity(ratingDbModel: RatingDbModel) = Rating(
        kp = ratingDbModel.kp
    )

    fun mapEntityToDbModel(movie: Movie) = MovieDbModel(
        id = movie.id,
        name = movie.name,
        description = movie.description,
        year = movie.year,
        poster = mapPosterEntityToPosterDb(movie.poster),
        rating = mapRatingEntityToRatingDb(movie.rating)
    )

    private fun mapPosterEntityToPosterDb(poster: Poster) = PosterDbModel(
        url = poster.url
    )

    private fun mapRatingEntityToRatingDb(rating: Rating) = RatingDbModel(
        kp = rating.kp
    )

    fun mapMovieDtoToEntity(dto: MovieDto) = Movie(
        id = dto.id,
        name = dto.name,
        description = dto.description,
        year = dto.year,
        poster = mapPosterDtoToPosterEntity(dto.poster),
        rating = mapRatingDtoToRatingEntity(dto.rating)
    )

    private fun mapPosterDtoToPosterEntity(posterDto: PosterDto) = Poster(
        url = posterDto.url
    )

    private fun mapRatingDtoToRatingEntity(ratingDto: RatingDto) = Rating(
        kp = ratingDto.kp
    )

    fun mapMovieResponseDtoToEntity(dto: MovieResponseDto) = MovieResponse(
        movies = mapMovesDtoListToEntityList(dto.movies)
    )

    private fun mapMovesDtoListToEntityList(list: List<MovieDto>): List<Movie> {
        return list.map {
            mapMovieDtoToEntity(it)
        }
    }

    fun mapTrailerResponseDtoToEntity(dto: TrailerResponseDto) = TrailerResponse(
        trailsList = mapVideoDtoToVideoEntity(dto.trailsList)
    )

    private fun mapVideoDtoToVideoEntity(videoDto: VideoDto) = Video(
        trailers = mapTrailersDtoListToEntityList(videoDto.trailers)
    )

    private fun mapTrailersDtoListToEntityList(list: List<TrailerDto>): List<Trailer> {
        return list.map {
            mapTrailerDtoToEntity(it)
        }
    }

    fun mapReviewResponseDtoToEntity(dto: ReviewResponseDto) = ReviewResponse(
        reviewList = mapReviewsDtoListToEntityList(dto.reviewList)
    )

    private fun mapReviewsDtoListToEntityList(list: List<ReviewDto>): List<Review> {
        return list.map {
            mapReviewDtoToEntity(it)
        }
    }

    fun mapReviewDtoToEntity(dto: ReviewDto) = Review(
        title = dto.title,
        type = dto.type,
        review = dto.review
    )

    fun mapTrailerDtoToEntity(dto: TrailerDto) = Trailer(
        name = dto.name,
        url = dto.url
    )
}