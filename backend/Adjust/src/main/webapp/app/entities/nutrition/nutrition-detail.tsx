import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './nutrition.reducer';
import { INutrition } from 'app/shared/model/nutrition.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface INutritionDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const NutritionDetail = (props: INutritionDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { nutritionEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="adjustApp.nutrition.detail.title">Nutrition</Translate> [<b>{nutritionEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="adjustApp.nutrition.name">Name</Translate>
            </span>
          </dt>
          <dd>{nutritionEntity.name}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="adjustApp.nutrition.description">Description</Translate>
            </span>
          </dt>
          <dd>{nutritionEntity.description}</dd>
          <dt>
            <span id="unit">
              <Translate contentKey="adjustApp.nutrition.unit">Unit</Translate>
            </span>
          </dt>
          <dd>{nutritionEntity.unit}</dd>
          <dt>
            <span id="adjustNutritionId">
              <Translate contentKey="adjustApp.nutrition.adjustNutritionId">Adjust Nutrition Id</Translate>
            </span>
          </dt>
          <dd>{nutritionEntity.adjustNutritionId}</dd>
          <dt>
            <span id="caloriesPerUnit">
              <Translate contentKey="adjustApp.nutrition.caloriesPerUnit">Calories Per Unit</Translate>
            </span>
          </dt>
          <dd>{nutritionEntity.caloriesPerUnit}</dd>
          <dt>
            <span id="proteinPerUnit">
              <Translate contentKey="adjustApp.nutrition.proteinPerUnit">Protein Per Unit</Translate>
            </span>
          </dt>
          <dd>{nutritionEntity.proteinPerUnit}</dd>
          <dt>
            <span id="carbsPerUnit">
              <Translate contentKey="adjustApp.nutrition.carbsPerUnit">Carbs Per Unit</Translate>
            </span>
          </dt>
          <dd>{nutritionEntity.carbsPerUnit}</dd>
          <dt>
            <span id="fatInUnit">
              <Translate contentKey="adjustApp.nutrition.fatInUnit">Fat In Unit</Translate>
            </span>
          </dt>
          <dd>{nutritionEntity.fatInUnit}</dd>
          <dt>
            <Translate contentKey="adjustApp.nutrition.meal">Meal</Translate>
          </dt>
          <dd>{nutritionEntity.mealId ? nutritionEntity.mealId : ''}</dd>
        </dl>
        <Button tag={Link} to="/nutrition" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/nutrition/${nutritionEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ nutrition }: IRootState) => ({
  nutritionEntity: nutrition.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(NutritionDetail);
