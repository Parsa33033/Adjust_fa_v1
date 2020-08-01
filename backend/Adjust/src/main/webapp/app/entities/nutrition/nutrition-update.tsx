import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IMeal } from 'app/shared/model/meal.model';
import { getEntities as getMeals } from 'app/entities/meal/meal.reducer';
import { getEntity, updateEntity, createEntity, reset } from './nutrition.reducer';
import { INutrition } from 'app/shared/model/nutrition.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface INutritionUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const NutritionUpdate = (props: INutritionUpdateProps) => {
  const [mealId, setMealId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { nutritionEntity, meals, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/nutrition');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getMeals();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...nutritionEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="adjustApp.nutrition.home.createOrEditLabel">
            <Translate contentKey="adjustApp.nutrition.home.createOrEditLabel">Create or edit a Nutrition</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : nutritionEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="nutrition-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="nutrition-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="nutrition-name">
                  <Translate contentKey="adjustApp.nutrition.name">Name</Translate>
                </Label>
                <AvField id="nutrition-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="nutrition-description">
                  <Translate contentKey="adjustApp.nutrition.description">Description</Translate>
                </Label>
                <AvField id="nutrition-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="unitLabel" for="nutrition-unit">
                  <Translate contentKey="adjustApp.nutrition.unit">Unit</Translate>
                </Label>
                <AvField id="nutrition-unit" type="string" className="form-control" name="unit" />
              </AvGroup>
              <AvGroup>
                <Label id="adjustNutritionIdLabel" for="nutrition-adjustNutritionId">
                  <Translate contentKey="adjustApp.nutrition.adjustNutritionId">Adjust Nutrition Id</Translate>
                </Label>
                <AvField id="nutrition-adjustNutritionId" type="string" className="form-control" name="adjustNutritionId" />
              </AvGroup>
              <AvGroup>
                <Label id="caloriesPerUnitLabel" for="nutrition-caloriesPerUnit">
                  <Translate contentKey="adjustApp.nutrition.caloriesPerUnit">Calories Per Unit</Translate>
                </Label>
                <AvField id="nutrition-caloriesPerUnit" type="string" className="form-control" name="caloriesPerUnit" />
              </AvGroup>
              <AvGroup>
                <Label id="proteinPerUnitLabel" for="nutrition-proteinPerUnit">
                  <Translate contentKey="adjustApp.nutrition.proteinPerUnit">Protein Per Unit</Translate>
                </Label>
                <AvField id="nutrition-proteinPerUnit" type="string" className="form-control" name="proteinPerUnit" />
              </AvGroup>
              <AvGroup>
                <Label id="carbsPerUnitLabel" for="nutrition-carbsPerUnit">
                  <Translate contentKey="adjustApp.nutrition.carbsPerUnit">Carbs Per Unit</Translate>
                </Label>
                <AvField id="nutrition-carbsPerUnit" type="string" className="form-control" name="carbsPerUnit" />
              </AvGroup>
              <AvGroup>
                <Label id="fatInUnitLabel" for="nutrition-fatInUnit">
                  <Translate contentKey="adjustApp.nutrition.fatInUnit">Fat In Unit</Translate>
                </Label>
                <AvField id="nutrition-fatInUnit" type="string" className="form-control" name="fatInUnit" />
              </AvGroup>
              <AvGroup>
                <Label for="nutrition-meal">
                  <Translate contentKey="adjustApp.nutrition.meal">Meal</Translate>
                </Label>
                <AvInput id="nutrition-meal" type="select" className="form-control" name="mealId">
                  <option value="" key="0" />
                  {meals
                    ? meals.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/nutrition" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  meals: storeState.meal.entities,
  nutritionEntity: storeState.nutrition.entity,
  loading: storeState.nutrition.loading,
  updating: storeState.nutrition.updating,
  updateSuccess: storeState.nutrition.updateSuccess,
});

const mapDispatchToProps = {
  getMeals,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(NutritionUpdate);
