import 'package:built_value/built_value.dart';
import 'package:built_value/serializer.dart';
import 'package:movie_app/models/app_user.dart';
import 'package:movie_app/models/serializers.dart';

part 'auth_state.g.dart';

abstract class AuthState implements Built<AuthState, AuthStateBuilder> {
  factory AuthState.initialState() {
    return _$AuthState((b) {});
  }

  factory AuthState.fromJson(dynamic json) => serializers.deserializeWith(serializer, json);

  AuthState._();

  @nullable
  AppUser get user;

  Map<String, dynamic> get json => serializers.serializeWith(serializer, this) as Map<String, dynamic>;

  static Serializer<AuthState> get serializer => _$authStateSerializer;
}