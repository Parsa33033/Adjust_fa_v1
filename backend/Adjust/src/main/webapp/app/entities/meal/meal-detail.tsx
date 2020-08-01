import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './meal.reducer';
import { IMeal } from 'app/shared/model/meal.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMealDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const MealDetail = (props: IMealDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { mealEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="adjustApp.meal.detail.title">Meal</Translate> [<b>{mealEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="adjustApp.meal.name">Name</Translate>
            </span>
          </dt>
          <dd>{mealEntity.name}</dd>
          <dt>
            <span id="number">
              <Translate contentKey="adjustApp.meal.number">Number</Translate>
            </span>
          </dt>
          <dd>{mealEntity.number}</dd>
          <dt>
            <Translate contentKey="adjustApp.meal.nutritionProgram">Nutrition Program</Translate>
          </dt>
          <dd>{mealEntity.nutritionProgramId ? mealEntity.nutritionProgramId : ''}</dd>
        </dl>
        <Button tag={Link} to="/meal" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/meal/${mealEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ meal }: IRootState) => ({
  mealEntity: meal.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MealDetail);
