import 'package:built_collection/built_collection.dart';
import 'package:meta/meta.dart';
import 'package:movie_app/actions/add_watched.dart';
import 'package:movie_app/actions/get_movies.dart';
import 'package:movie_app/actions/get_movies_by_name.dart';
import 'package:movie_app/actions/index.dart';
import 'package:movie_app/actions/to_watch.dart';
import 'package:movie_app/actions/view_movie.dart';
import 'package:movie_app/data/api.dart';
import 'package:movie_app/models/movie.dart';
import 'package:movie_app/models/states/app_state.dart';
import 'package:redux_epics/redux_epics.dart';
import 'package:rxdart/rxdart.dart';

class MoviesEpics {
  const MoviesEpics({@required AppApi api})
      : assert(api != null),
        _api = api;

  final AppApi _api;

  Epic<AppState> get epics {
    return combineEpics(<Epic<AppState>>[
      TypedEpic<AppState, GetMovies$>(_getMovies),
      TypedEpic<AppState, GetMoviesByName$>(_getMoviesByName),
      TypedEpic<AppState, ViewMovie$>(_viewMovie),
      TypedEpic<AppState, ToWatch$>(_toWatch),
      TypedEpic<AppState, AddWatched$>(_addWatched),
    ]);
  }

  Stream<AppAction> _getMovies(Stream<GetMovies$> actions, EpicStore<AppState> store) {
    return actions //
        .flatMap((GetMovies$ action) =>
        Stream<GetMovies$>.value(action)
            .asyncMap((GetMovies$ action) => _api.getMovies(store.state.moviesState.genre, store.state.moviesState.page))
            .map((BuiltList<Movie> movies) => GetMovies.successful(movies))
            .onErrorReturnWith((dynamic error) => GetMovies.error(error)));
  }

  Stream<AppAction> _getMoviesByName(Stream<GetMoviesByName$> actions, EpicStore<AppState> store) {
    return actions //
        .flatMap((GetMoviesByName$ action) =>
        Stream<GetMoviesByName$>.value(action)
            .asyncMap((GetMoviesByName$ action) => _api.getMoviesByName(action.movieName))
            .map((BuiltList<Movie> movies) => GetMoviesByName.successful(movies))
            .onErrorReturnWith((dynamic error) => GetMoviesByName.error(error)));
  }

  Stream<AppAction> _viewMovie(Stream<ViewMovie$> actions, EpicStore<AppState> store) {
    return actions //
        .flatMap((ViewMovie$ action)  =>
        Stream<ViewMovie$>.value(action)
            .asyncMap((ViewMovie$ action) async => await _api.viewMovie(action.movieId))
            .map((Movie movie) => ViewMovie.successful(movie))
            .onErrorReturnWith((dynamic error) => ViewMovie.error(error)));
  }

  Stream<AppAction> _toWatch(Stream<ToWatch$> actions, EpicStore<AppState> store) {
    return actions //
        .flatMap((ToWatch$ action)  =>
        Stream<ToWatch$>.value(action)
            .asyncMap((ToWatch$ action) async => await _api.addToWatch(user: action.user, movieId: action.movieId))
            .map((String movieId) => ToWatch.successful(movieId))
            .onErrorReturnWith((dynamic error) => ToWatch.error(error)));
  }

  Stream<AppAction> _addWatched(Stream<AddWatched$> actions, EpicStore<AppState> store) {
    return actions //
        .flatMap((AddWatched$ action)  =>
        Stream<AddWatched$>.value(action)
            .asyncMap((AddWatched$ action) async => await _api.addWatched(user: action.user, movieId: action.movieId))
            .map((String movieId) => AddWatched.successful(movieId))
            .onErrorReturnWith((dynamic error) => AddWatched.error(error)));
  }
}