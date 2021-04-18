import 'package:built_collection/built_collection.dart';
import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:movie_app/models/movie.dart';

import 'index.dart';

part 'get_movies_by_name.freezed.dart';

@freezed
abstract class GetMoviesByName with _$GetMoviesByName implements AppAction {
  const factory GetMoviesByName(String movieName) = GetMoviesByName$;

  const factory GetMoviesByName.successful(BuiltList<Movie> movies) = GetMoviesByNameSuccessful;

  const factory GetMoviesByName.error(dynamic error) = GetMoviesByNameError;
}