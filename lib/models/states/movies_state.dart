import 'package:built_collection/built_collection.dart';
import 'package:built_value/built_value.dart';
import 'package:built_value/serializer.dart';

import '../movie.dart';
import '../serializers.dart';
import 'auth_state.dart';

part 'movies_state.g.dart';

abstract class MoviesState implements Built<MoviesState, MoviesStateBuilder> {
  factory MoviesState.initialState() {
    return _$MoviesState((b) {});
  }

  factory MoviesState.fromJson(dynamic json) => serializers.deserializeWith(serializer, json);

  MoviesState._();

  @nullable
  BuiltList<Movie> get movies;

  Map<String, dynamic> get json => serializers.serializeWith(serializer, this) as Map<String, dynamic>;

  static Serializer<MoviesState> get serializer => _$moviesStateSerializer;
}