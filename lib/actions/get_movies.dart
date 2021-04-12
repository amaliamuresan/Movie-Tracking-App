import 'package:built_collection/built_collection.dart';
import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:movie_app/actions/index.dart';
import 'package:movie_app/models/movie.dart';

part 'get_movies.freezed.dart';

@freezed
abstract class GetMovies with _$GetMovies implements AppAction {
  const factory GetMovies() = GetMovies$;

  const factory GetMovies.successful(BuiltList<Movie> movies) = GetMoviesSuccessful;

  const factory GetMovies.error(dynamic error) = GetMoviesError;
}