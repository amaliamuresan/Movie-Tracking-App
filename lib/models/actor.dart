import 'package:built_collection/built_collection.dart';
import 'package:built_value/built_value.dart';
import 'package:built_value/serializer.dart';
import 'package:movie_app/models/serializers.dart';

part 'actor.g.dart';

abstract class Actor implements Built<Actor, ActorBuilder> {
  factory Actor([void Function(ActorBuilder) updates]) = _$Actor;

  factory Actor.fromJson(dynamic json) {
    return serializers.deserializeWith(serializer, json);
  }

  Actor._();

  @nullable
  String get name;

  @nullable
  String get profile_path;

  @nullable
  String get character;

  Map<String, dynamic> get json => serializers.serializeWith(serializer, this) as Map<String, dynamic>;
  static Serializer<Actor> get serializer => _$actorSerializer;
}