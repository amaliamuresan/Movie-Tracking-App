import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:movie_app/models/movie.dart';

import 'index.dart';

part 'view_movie.freezed.dart';

@freezed
abstract class ViewMovie with _$ViewMovie implements AppAction {
  const factory ViewMovie(String movieId) = ViewMovie$;

  const factory ViewMovie.successful(Movie movie) = ViewMovieSuccessful;

  const factory ViewMovie.error(dynamic error) = ViewMovieError;
}